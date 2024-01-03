package com.hxl.forum.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class AdminTokenHelper {
    public Integer getAidFromSession(HttpSession session)
    {
        return (Integer) session.getAttribute("aid");
    }
}
