package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("hello")
    public String hello2(){
        return "views/hello2";
    }

    @GetMapping("hello")
    public String hello(){
        return "views/index";
    }

    @PostMapping("hello")
    public String hello1(){
        return "views/hello1";
    }

}
