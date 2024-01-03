package com.hxl.forum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossOriginConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(("/**"))
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(18000000)
                //用户和管理员是两个app
                .allowedOrigins("http://localhost:5173",
                        "http://localhost:5175");
    }
}
