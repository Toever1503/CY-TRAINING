package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("123.....");
//        registry.addResourceHandler("/test/**").addResourceLocations("file:/E:/relax/anime news fanpage/15 july birthday");
        registry.addResourceHandler("/test/**").addResourceLocations("file:/E:/relax/test12/");
    }
}
