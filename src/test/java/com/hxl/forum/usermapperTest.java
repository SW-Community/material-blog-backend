package com.hxl.forum;

import com.hxl.forum.entity.Admin;
import com.hxl.forum.entity.Users;
import com.hxl.forum.mapper.AdminMapper;
import com.hxl.forum.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class usermapperTest {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Test
    public void insert(){
        Users users=new Users();
        users.setUserName("root");
        users.setPassword("123456");
        Integer row=usersMapper.insert(users);
        System.out.println(row);
    }
    @Test
    public void findByName(){
        String name="admin";
        Users users=usersMapper.findByUsername(name);
        System.out.println(users);
    }
    @Test
    public void updatePasswordByUid() {
//        Integer uid = 7;
//        String password = "123456";
//        Integer rows = usersMapper.updatePasswordByUid(uid, password);
//        System.out.println("rows=" + rows);
    }
    @Test
    public void findByUid() {
        Integer uid = 1;
        Users result = usersMapper.findByUid(uid);
        System.out.println(result);
    }
    @Test
    public void updateInfoByUid() {
        Users user = new Users();
        user.setUserID(1);
        user.setEmail("admin@cy.com");
        user.setGender(1);
        Integer rows = usersMapper.updateInfoByUid(user);
        System.out.println("rows=" + rows);
    }
    @Test
    public void findByAname(){
        Admin admin=adminMapper.findByAdminName("admin");
        System.out.println(admin);
    }
}
