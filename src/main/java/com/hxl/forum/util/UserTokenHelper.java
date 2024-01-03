package com.hxl.forum.util;

import com.hxl.forum.entity.Users;
import com.hxl.forum.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
 @Component
public class UserTokenHelper {
    @Autowired
    IUsersService usersService;
    public Users getAndVerifyUserFromSession(HttpSession session)
    {
        Integer uid= (Integer) session.getAttribute("uid");
        return usersService.getByUid(uid);
    }

     public Integer getUidFromSession(HttpSession session)
     {
        return (Integer) session.getAttribute("uid");
     }

     public String getUserNameFromSession(HttpSession session)
     {
         return (String) session.getAttribute("username");
     }
}
