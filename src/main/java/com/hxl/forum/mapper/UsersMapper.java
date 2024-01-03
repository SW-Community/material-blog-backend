package com.hxl.forum.mapper;

import com.hxl.forum.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    List<Users> getAll();
    Integer insert(Users user);
    Users findByUsername(String username);
    Integer updatePasswordByUid(@Param("userID") Integer uid, @Param("password") String password);
    Users findByUid(Integer uid);
    Integer delete(Integer uid);
    Integer recover(Integer uid);
    List<Users> getAllDeleted();
    Integer recoverAll();
    //清空回收站
    Integer clear(Integer uid);
    Integer clearAll();

    Integer updateInfoByUid(Users user);
    Integer updateAvatarByUid(@Param("userID") Integer uid, @Param("profile") String profile);

    Integer getUserCount();

    List<Users> getUsersByPage(Integer start,Integer end);
}
