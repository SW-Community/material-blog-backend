package com.hxl.forum.controller.admin;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.Admin;
import com.hxl.forum.service.IAdminService;
import com.hxl.forum.util.AdminTokenHelper;
import com.hxl.forum.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("admin/admins")
public class AdminController extends BaseController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private AdminTokenHelper adminTokenHelper;

    //todo:也许会开
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    static {
        AVATAR_TYPES.add("image/jpg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    @RequestMapping("login")
    public JsonResult<Admin> login(String adminName, String password,HttpSession session){
        System.out.println("mylyh=================");
        System.out.println(adminName+" "+password);
        Admin data = adminService.login(adminName, password);
        session.setAttribute("aid", data.getAid());
        session.setAttribute("adminname", data.getAdminName());
        return new JsonResult<>(OK, data);
    }
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session){
        Integer aid = adminTokenHelper.getAidFromSession(session);
        adminService.changePassword(aid, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("log_out")
    public JsonResult<Void> logout(HttpSession session)
    {
        session.removeAttribute("aid");
        session.removeAttribute("adminnname");
        return new JsonResult<>(OK);
    }
}


