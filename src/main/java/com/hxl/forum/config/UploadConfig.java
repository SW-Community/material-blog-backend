package com.hxl.forum.config;

import com.hxl.forum.util.RealPathHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 设置虚拟路径，访问绝对路径下资源
 */
@Configuration
public class UploadConfig implements WebMvcConfigurer {

    @Autowired
    private RealPathHelper realPathHelper;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String realPath=realPathHelper.getRealPath();
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/static/upload/**").addResourceLocations("file:"+realPath+"\\");
        WebMvcConfigurer.super.addResourceHandlers(registry);

        //这个是错误滴
        //"file:E:\\JAVAproject\\forum\\src\\main\\resources\\static\\upload"
    }
}