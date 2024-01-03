package com.hxl.forum.config;

import com.hxl.forum.interceptor.AdminInterceptor;
import com.hxl.forum.interceptor.SuInterceptor;
import com.hxl.forum.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        UserInterceptor interceptor=new UserInterceptor();
        AdminInterceptor adminInterceptor=new AdminInterceptor();
        SuInterceptor suInterceptor=new SuInterceptor();

        //不登陆可以浏览，但无法发贴或回复
        //todo:后续增加图片功能后设置拦截器确保可以阅读

        List<String> excepted=new ArrayList<>();

        //不然会莫名其妙被拦截从而定位不到bug
        //表现为拦截器执行，从而让人误以为是被拦截了而掩盖了真正的bug
        excepted.add("/*");
        excepted.add("/index.html");
        excepted.add("/error");
        excepted.add("/static/**");

        //下面是正常的业务选项
        excepted.add("/admin/**");
        excepted.add("/users/login");
        excepted.add("/users/get_by_uid");
        excepted.add("/users/reg");
        excepted.add("/topics/getall");
        excepted.add("/mposts/get_all");
        excepted.add("/mposts/get_all_preview");
        excepted.add("/mposts/get_by_mid");
        excepted.add("/mposts/get_by_tid");
        excepted.add("/mposts/get_by_day");
        excepted.add("/fposts/get_by_mid");
        excepted.add("/postimg/get_by_pid");

        registry.addInterceptor(interceptor).order(1).addPathPatterns("/**").excludePathPatterns(excepted);

        List<String> adminExcepted=new ArrayList<>();
        adminExcepted.add("/*");
        adminExcepted.add("/index.html");
        adminExcepted.add("/error");
        adminExcepted.add("/static/**");

        adminExcepted.add("/admin/admins/login");
        adminExcepted.add("/admin/su/**");
        registry.addInterceptor(adminInterceptor).order(2).addPathPatterns("/admin/**").excludePathPatterns(adminExcepted);

        List<String> suExcepted=new ArrayList<>();

        suExcepted.add("/*");
        suExcepted.add("/index.html");
        suExcepted.add("/error");
        suExcepted.add("/static/**");

        registry.addInterceptor(suInterceptor).order(3).addPathPatterns("/admin/su/**").excludePathPatterns(suExcepted);

    }
}
