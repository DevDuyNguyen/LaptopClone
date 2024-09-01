package com.example.LaptopShopClone.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MyWebMVCConfig implements WebMvcConfigurer{
	@Value("${upload_dir}")
	String upload_dir;
	@Value("${upload_img_dir}")
	String upload_img_dir;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registery) {
		registery.addResourceHandler("/HinhAnhSP/**").addResourceLocations("file:"+upload_img_dir+"/");
	}
}
