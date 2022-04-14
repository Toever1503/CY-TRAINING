package com.config;

import com.config.filter.AdminFilter;
import com.config.filter.MyCartFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // đánh dấu đây là lớp cấu hình
public class WebConfiguration implements WebMvcConfigurer {
    public static String imageFolder; // biến public static để truy cập tên folder upload ảnh trên disk
    private final String rootFolder; // biến value lấy từ config: application.properties

    // autowired bằng constructor
    public WebConfiguration(@Value("${imageFolder}") String rootFolder) {
        this.rootFolder = rootFolder; // lấy từ config: application.properties
        this.imageFolder = this.rootFolder; // set giá trị cho biến public static
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // map url path /images để truy cập folder upload ảnh trên disk
        registry.addResourceHandler("/images/**").addResourceLocations("file:/" + rootFolder);
    }

//    @Bean
//    public FilterRegistrationBean<MyCartFilter> myCartFilterFilterRegistrationBean() {
//        FilterRegistrationBean<MyCartFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new MyCartFilter());
//        registrationBean.addUrlPatterns("/my-cart/*");
//        return registrationBean;
//    }
//
//    @Bean
//    FilterRegistrationBean<AdminFilter> adminFilterFilterRegistrationBean() {
//        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AdminFilter());
//        registrationBean.addUrlPatterns("/admin/*", "/my-order/*", "/checkout/*");
//        return registrationBean;
//    }

}
