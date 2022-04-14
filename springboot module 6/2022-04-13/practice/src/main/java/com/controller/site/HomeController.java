package com.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "site/index";
    }

    @GetMapping("category/{id}")
    public String category(@PathVariable Long id, Model model) {
        model.addAttribute("catId", id);
        return "site/category";
    }
}
