package com.example.demo1.controller;

import com.example.demo1.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("model", new User());
        return "index";
    }

    @PostMapping("/login")
    String login(User u) {
        System.out.println(u);
        return "index1";
    }
}
