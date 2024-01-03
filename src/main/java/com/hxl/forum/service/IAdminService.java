package com.hxl.forum.service;

import com.hxl.forum.entity.Admin;

public interface IAdminService {
    Admin login(String name,String password);
    void changePassword(Integer aid, String oldPassword, String newPassword);
    void reg(Admin admin);
}
