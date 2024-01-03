package com.hxl.forum.service.impl;

import com.hxl.forum.entity.Users;
import com.hxl.forum.mapper.UsersMapper;
import com.hxl.forum.service.IUsersService;
import com.hxl.forum.service.ex.*;
import com.hxl.forum.service.ex.user.UserNotFoundException;
import com.hxl.forum.service.ex.user.UsernameDuplicateException;
import com.hxl.forum.util.EncryptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUsersService {
    @Resource
    private UsersMapper usersMapper;

    @Value("${user.init_password}")
    private String initPassword;

    //todo:清理耦合代码，改用组件实现
    @Autowired
    private EncryptionHelper encryptionHelper;

    @Override
    public List<Users> getAll() {
        List<Users> result=usersMapper.getAll();
        if(result == null)
            throw new UserNotFoundException("暂无相关用户");
        return result;
    }

    @Override
    public void reg(Users users) {
        String username = users.getUserName();
        // 调用持久层的User findByUsername(String username)方法，根据用户名查询用户数据
        Users result = usersMapper.findByUsername(username);
        // 判断查询结果是否不为null
        if (result != null) {
            // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
            throw new UsernameDuplicateException("尝试注册的用户名[" + username +
                    "]已经被占用");
        }
        // 补全数据：加密后的密码
        String salt= encryptionHelper.emitSalt();
        //String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = encryptionHelper.getMd5Password(users.getPassword(), salt);
        users.setPassword(md5Password);
        // 补全数据
        users.setSalt(salt);
        users.setIsDelete(0);
        // 表示用户名没有被占用，则允许注册
        // 调用持久层Integer insert(User user)方法，执行注册并获取返回值(受影响的行数)
        Integer rows = usersMapper.insert(users);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public Users login(String username, String password) {
        Users result = usersMapper.findByUsername(username);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在的错误");
        }
        String salt = result.getSalt();
        String md5Password = encryptionHelper.getMd5Password(password, salt);
        if (!result.getPassword().equals(md5Password)) {
            throw new PasswordNotMatchException("密码验证失败的错误");
        }

        Users user = new Users();

        user.setUserID(result.getUserID());
        user.setUserName(result.getUserName());
        user.setGender(result.getGender());
        user.setEmail(result.getEmail());
        user.setProfile(result.getProfile());
// 返回新的user对象
        return user;
    }

    @Override
    public void changePassword(Integer uid, String oldPassword, String newPassword) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        Users result = usersMapper.findByUid(uid);
        // 检查查询结果是否为null
        if (result == null) {
// 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
// 检查查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
// 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
// 从查询结果中取出盐值
        String salt = result.getSalt();
// 将参数oldPassword结合盐值加密，得到oldMd5Password
        String oldMd5Password = encryptionHelper.getMd5Password(oldPassword, salt);
// 判断查询结果中的password与oldMd5Password是否不一致
        if (!result.getPassword().contentEquals(oldMd5Password)) {
// 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("原密码错误");
        }
// 将参数newPassword结合盐值加密，得到newMd5Password
        String newMd5Password = encryptionHelper.getMd5Password(newPassword, salt);
// 调用userMapper的updatePasswordByUid()更新密码，并获取返回值
        Integer rows = usersMapper.updatePasswordByUid(uid, newMd5Password);
// 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
// 是：抛出UpdateException异常
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public Users getByUid(Integer uid) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        Users result = usersMapper.findByUid(uid);
// 判断查询结果是否为null
        if (result == null) {
// 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
// 判断查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
// 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
// 创建新的User对象
        Users user = new Users();
// 将以上查询结果中的username/email/gender封装到新User对象中
        user.setUserID(result.getUserID());
        user.setUserName(result.getUserName());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        user.setProfile((result.getProfile()));
        return user;
    }

    @Override
    public void changeInfo(Integer uid, Users user) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        Users result = usersMapper.findByUid(uid);
// 判断查询结果是否为null
        if (result == null) {
// 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
// 判断查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
// 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
// 向参数user中补全数据：uid
        user.setUserID(uid);
// 调用userMapper的updateInfoByUid(User user)方法执行修改，并获取返回值
        Integer rows = usersMapper.updateInfoByUid(user);
// 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
// 是：抛出UpdateException异常
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        Users result = usersMapper.findByUid(uid);
        // 检查查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }
        // 检查查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }
        // 调用userMapper的updateAvatarByUid()方法执行更新，并获取返回值
        Integer rows = usersMapper.updateAvatarByUid(uid, avatar);
        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }


    @Override
    public Integer getUserCount() {
        return usersMapper.getUserCount();
    }

    @Override
    public Integer getUserPageNum(Integer pageSize) {
        if(pageSize<=0)
        {
            throw new PageSizeException("分页设置错误");
        }
        Integer sum=getUserCount();
        return (sum+pageSize-1)/pageSize;
    }

    @Override
    public List<Users> getAUserPage(Integer pageNo, Integer pageSize) {
        Integer pageNum=getUserPageNum(pageSize);
        if(pageNo<1)
        {
            pageNo=1;
        }
        if(pageNo>pageNum)
        {
            pageNo=pageNum;
        }
        int start=(pageNo-1)*pageSize;
        int end=pageSize;
        return usersMapper.getUsersByPage(start,end);
    }

    @Override
    public void banUser(Integer id) {
        Users users= usersMapper.findByUid(id);
        if(users==null)
        {
            throw new UserNotFoundException("用户不存在");
        }
        Integer rows=usersMapper.delete(id);
        if(rows==0)
        {
            throw new UpdateException("数据库错误");
        }
    }

    @Override
    public void resetPassword(Integer id) {
        Users users= usersMapper.findByUid(id);
        if(users==null)
        {
            throw new UserNotFoundException("用户不存在");
        }
        String password=initPassword;
        String salt= users.getSalt();
        String md5Password=encryptionHelper.getMd5Password(password,salt);

        usersMapper.updatePasswordByUid(id,md5Password);
    }

//    @Deprecated
//    private String getMd5Password(String password, String salt) {
//        for (int i = 0; i < 3; i++) {
//            password = DigestUtils.md5DigestAsHex((salt + password +
//                    salt).getBytes()).toUpperCase();
//        }
//        return password;
//    }
}
