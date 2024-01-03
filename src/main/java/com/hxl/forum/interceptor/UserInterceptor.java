package com.hxl.forum.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxl.forum.service.ex.user.NotLoginException;
import com.hxl.forum.util.JsonResult;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("uid")==null)
        {
            //System.out.println("芭比Q了");

            System.out.println(request.getRequestURL()+"，方法："+request.getMethod()+"，被"+this.getClass().getName()+"拦截了");
            //返回一个错误信息
            NotLoginException e=new NotLoginException("登录过期或未登录，请重新登陆");
            JsonResult<Void> r=new JsonResult<>(e);
            r.setState(4003);
            ObjectMapper mapper=new ObjectMapper();
            String ret=mapper.writeValueAsString(r);
            response.setCharacterEncoding ("UTF-8");
            response.getWriter().write(ret);
            return false;
        }
        return true;
    }
}
