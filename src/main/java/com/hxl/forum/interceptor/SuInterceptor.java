package com.hxl.forum.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxl.forum.service.ex.user.NotLoginException;
import com.hxl.forum.util.JsonResult;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session= request.getSession();
        Integer aid = (Integer) session.getAttribute("aid");
        if(aid==null||aid!=-2)
        {
            System.out.println(request.getRequestURL()+"，方法："+request.getMethod()+"，被"+this.getClass().getName()+"拦截了");
            NotLoginException e=new NotLoginException("不是超级用户，无权限");
            JsonResult<Void> r=new JsonResult<>(e);
            r.setState(4003);
            ObjectMapper mapper=new ObjectMapper();
            String ret=mapper.writeValueAsString(r);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(ret);
            return false;
        }
        return true;
    }
}
