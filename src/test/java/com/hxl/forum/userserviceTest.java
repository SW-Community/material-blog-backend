package com.hxl.forum;

import com.hxl.forum.entity.Users;
import com.hxl.forum.service.ex.ServiceException;
import com.hxl.forum.service.impl.AdminServiceImpl;
import com.hxl.forum.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class userserviceTest {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AdminServiceImpl adminService;
    @Test
    public void changePassword() {
        try {
            Integer uid = 3;
            String oldPassword = "123";
            String newPassword = "888888";
            userService.changePassword(uid, oldPassword, newPassword);
            System.out.println("密码修改成功！");
        } catch (ServiceException e) {
            System.out.println("密码修改失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void getByUid() {
        try {
            Integer uid = 1;
            Users user = userService.getByUid(uid);
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void changeInfo() {
        try {
            Integer uid = 7;
            Users user = new Users();
            user.setEmail("admin03@cy.cn");
            user.setGender(2);
            userService.changeInfo(uid, user);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void Login(){
        adminService.login("admin","1");
    }
}
