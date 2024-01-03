package com.hxl.forum.service;

import com.hxl.forum.entity.Users;

import java.util.List;

public interface IUsersService {
    @Deprecated
    List<Users> getAll();
    void reg(Users users);
    Users login(String username, String password);
    public void changePassword(Integer uid, String oldPassword, String newPassword);
    Users getByUid(Integer uid);
    void changeInfo(Integer uid, Users user);
    void changeAvatar(Integer uid, String avatar);

    Integer getUserCount();
    Integer getUserPageNum(Integer pageSize);
    List<Users> getAUserPage(Integer pageNo,Integer pageSize);
    void banUser(Integer id);
    void resetPassword(Integer id);

}
