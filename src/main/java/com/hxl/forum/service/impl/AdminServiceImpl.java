package com.hxl.forum.service.impl;

import com.hxl.forum.entity.Admin;
import com.hxl.forum.mapper.AdminMapper;
import com.hxl.forum.service.IAdminService;
import com.hxl.forum.service.ex.InsertException;
import com.hxl.forum.service.ex.PasswordNotMatchException;
import com.hxl.forum.service.ex.UpdateException;
import com.hxl.forum.service.ex.admin.AdminnameDuplicateException;
import com.hxl.forum.service.ex.admin.SuReadonlyEception;
import com.hxl.forum.service.ex.user.UserNotFoundException;
import com.hxl.forum.util.EncryptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class AdminServiceImpl implements IAdminService {
    @Resource
    private AdminMapper adminMapper;

    @Autowired
    private EncryptionHelper encryptionHelper;

    public static final int SU_UID=-2;

    //调试后门
    @Value("${su.name}")
    private String suName;
    @Value("${su.password}")
    private String suPassword;

    @Override
    public Admin login(String name, String password) {
        //超级版主后门
        if(suName.equals(name))
        {
            System.out.println("backdoor activated");
            if(suPassword.equals(password))
            {
                Admin admin = new Admin();
                admin.setAid(SU_UID);
                admin.setAdminName(suName);
                return admin;
            }
            else {
                throw new PasswordNotMatchException("超级版主密码不正确");
            }
        }
        Admin result = adminMapper.findByAdminName(name);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在的错误");
        }
        String salt= result.getSalt();
        String passwordI = result.getPassword();
        String md5Password= encryptionHelper.getMd5Password(password,salt);
        if (!Objects.equals(md5Password, passwordI)) {
            throw new PasswordNotMatchException("密码验证失败的错误");
        }
        Admin admin = new Admin();
        admin.setAid(result.getAid());
        admin.setAdminName(result.getAdminName());
        return admin;
    }

    @Override
    public void changePassword(Integer aid, String oldPassword, String newPassword) {
        if(aid==SU_UID)
        {
            throw new SuReadonlyEception("超级版主密码不可修改，请在配置文件设置，然后重新启动应用程序");
        }
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        Admin result = adminMapper.findByAid(aid);
        // 检查查询结果是否为null
        if (result == null) {
// 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("管理员数据不存在，请联系超级版主");
        }
        if (!Objects.equals(result.getPassword(), oldPassword)) {
// 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("原密码错误");
        }
// 调用userMapper的updatePasswordByUid()更新密码，并获取返回值
        Integer rows = adminMapper.updatePasswordByAid(aid, Integer.parseInt(newPassword));
// 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
// 是：抛出UpdateException异常
            throw new UpdateException("更新管理员数据时出现未知错误，请联系超级版主");
        }
    }

    @Override
    public void reg(Admin admin) {
        String adminName=admin.getAdminName();
        if(suPassword.equals(adminName))
        {
            throw new AdminnameDuplicateException("用户名已被占用");
        }
        Admin result=adminMapper.findByAdminName(adminName);
        if(result!=null)
        {
            throw new AdminnameDuplicateException("名称已被占用");
        }

        String salt=encryptionHelper.emitSalt();
        String md5Password= encryptionHelper.getMd5Password(admin.getPassword(),salt);
        admin.setPassword(md5Password);
        admin.setSalt(salt);

        Integer rows=adminMapper.insert(admin);
        if (rows != 1) {
// 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }
    }


}
