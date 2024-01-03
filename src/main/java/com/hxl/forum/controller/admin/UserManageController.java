package com.hxl.forum.controller.admin;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.Users;
import com.hxl.forum.service.IUsersService;
import com.hxl.forum.util.EncryptionHelper;
import com.hxl.forum.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("admin/users")
public class UserManageController extends BaseController {

    @Autowired
    private IUsersService usersService;

    @Value("${list.page_size}")
    private int PAGE_SIZE;

    @RequestMapping("get_user_page_count")
    public JsonResult<Integer> getUserPageCount()
    {
        Integer res=usersService.getUserPageNum(PAGE_SIZE);
        return new JsonResult<>(OK,res);
    }

    @RequestMapping("get_user_by_page")
    public JsonResult<List<Users>> getUserByPage(Integer pageNo)
    {
        List<Users> ret=usersService.getAUserPage(pageNo,PAGE_SIZE);
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("update_user")
    public JsonResult<Void> updateUser(Integer uid,Users user)
    {
        usersService.changeInfo(uid,user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("reset_avatar")
    public JsonResult<Void> resetAvatar(Integer uid)
    {
        usersService.changeAvatar(uid,"");
        //todo:删除文件服务器上面的文件
        return new JsonResult<>(OK);
    }

    @RequestMapping("reset_password")
    public JsonResult<Void> resetPassword(Integer uid)
    {
        usersService.resetPassword(uid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("ban_user")
    public JsonResult<Void> banUser(Integer uid)
    {
        usersService.banUser(uid);
        return new JsonResult<>(OK);
    }
}
