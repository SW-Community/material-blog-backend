package com.hxl.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

@Configuration
@SpringBootApplication()
@MapperScan("com.hxl.forum.mapper")
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

	@Bean
	public MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// DataSize dataSize = DataSize.ofMegabytes(10);
		// 设置文件最大10M，DataUnit提供5中类型B,KB,MB,GB,TB
		factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
		factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
		// 设置总上传数据总大小10M
		return factory.createMultipartConfig();
	}
}
