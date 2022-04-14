package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpSession;

@Controller
public class HelloController {

    @GetMapping
    public String hello(HttpSession session) {
        session.setAttribute("name", "zhangsan");
        session.setMaxInactiveInterval(60);
        return "hello";
    }

    @GetMapping("/hello")
    public String hello2() {
        return "hello";
    }
}
