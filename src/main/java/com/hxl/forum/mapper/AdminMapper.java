package com.hxl.forum.mapper;

import com.hxl.forum.entity.Admin;
import com.hxl.forum.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    Admin findByAdminName(String adminname);
    Integer updatePasswordByAid(@Param("aid") Integer aid, @Param("password") Integer password);
    Admin findByAid(Integer aid);
    Integer insert(Admin admin);
    Integer recover(Integer aid);
    List<Admin> getAllDeleted();
    List<Admin> getAll();

    Admin getByAid(Integer aid);
    Integer recoverAll();

    Integer clear(Integer aid);
    Integer clearAll();

    Integer delete(Integer aid);
}
