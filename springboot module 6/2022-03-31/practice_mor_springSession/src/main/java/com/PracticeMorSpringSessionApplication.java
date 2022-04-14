package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.ServletContext;

@SpringBootApplication
@EnableJdbcHttpSession
public class PracticeMorSpringSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeMorSpringSessionApplication.class, args);
    }

//    @Bean
//    public ResourceHandlerRegistry resourceHandlerRegistry() {
//        ResourceHandlerRegistry registry = new ResourceHandlerRegistry(applicationContext, servletContext);
//        registry.addResourceHandler("/files/**").addResourceLocations("file:E:\\relax\\anime news fanpage\\15 july birthday");
//        return registry;
//    }

}
