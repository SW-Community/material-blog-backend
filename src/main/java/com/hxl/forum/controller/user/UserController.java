package com.hxl.forum.controller.user;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.Users;
import com.hxl.forum.service.IUsersService;
import com.hxl.forum.service.ex.*;
import com.hxl.forum.util.JsonResult;
import com.hxl.forum.util.RealPathHelper;
import com.hxl.forum.util.UserTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUsersService usersService;

    //TODO:清理耦合代码
    @Autowired
    private UserTokenHelper userTokenHelper;

    @Autowired
    private RealPathHelper realPathHelper;

    /** 头像文件大小的上限值(10MB) */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    @RequestMapping("get_all")
    public JsonResult<List<Users>> getAll(){
        List<Users>data=usersService.getAll();
        return new JsonResult<>(OK,data);
    }

    //todo:废弃旧接口
    @RequestMapping("get_by_uid")
    public JsonResult<Users> getByUid(Integer uid,HttpSession session) {
        uid = userTokenHelper.getUidFromSession(session);
        System.out.println("---"+uid);
        Users data = usersService.getByUid(uid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("reg")
    public JsonResult<Void> reg(Users user) {
        usersService.reg(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<Users> login(String username, String password, HttpSession session) {
        System.out.println("刘一涵-----------------"+username+" "+password);
        Users data = usersService.login(username, password);
        session.setAttribute("uid", data.getUserID());
        session.setAttribute("username", data.getUserName());
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword,HttpSession session) {
        Integer uid =  userTokenHelper.getUidFromSession(session);
        usersService.changePassword(uid, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file,HttpSession session /*, HttpServletRequest httpServletRequest*/) {
        // 判断上传的文件是否为
        Integer uid=userTokenHelper.getUidFromSession(session);

        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }

        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // public boolean list.contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false。
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES);
        }

        // 获取当前项目的绝对磁盘路径
        String parent= realPathHelper.getRealPath();
//        String parent=System.getProperty("user.dir");
//        parent=parent+"\\src\\main\\resources\\static\\upload";
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = 0;
        if (originalFilename != null) {
            beginIndex = originalFilename.lastIndexOf(".");
        }
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }

        // 头像路径
        String avatar = "/static/upload/" + filename;
        // 从Session中获取uid和username
        //Integer uid = getUidFromSession(session);
        // 将头像写入到数据库中
        usersService.changeAvatar(uid, filename);

        // 返回成功头像路径
        return new JsonResult<>(OK, avatar);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(Users user,HttpSession session/*, @RequestHeader("uid") int uid*/) {
// 从HttpSession对象中获取uid和username
        Integer uid = userTokenHelper.getUidFromSession(session);
        //String username = getUsernameFromSession(session);
// 调用业务对象执行修改用户资料
        usersService.changeInfo(uid, user);
// 响应成功
        return new JsonResult<>(OK);
    }


    @RequestMapping("logout")
    public JsonResult<Void> logout(HttpSession session)
    {
        session.removeAttribute("uid");
        session.removeAttribute("username");
        return new JsonResult<>(OK);
    }


/*
    @Deprecated
    private Integer getUidFromSession(HttpSession session)
    {
        Users user=getUserFromSession(session);
        if(user==null)
        {
            return -1;
        }
        return  user.getUserID();
    }

    @Deprecated
    private Users getUserFromSession(HttpSession session)
    {
        Integer uid= (Integer) session.getAttribute("uid");
        System.out.println("====lyh====="+uid);
        return usersService.getByUid(uid);
    }
*/
}
