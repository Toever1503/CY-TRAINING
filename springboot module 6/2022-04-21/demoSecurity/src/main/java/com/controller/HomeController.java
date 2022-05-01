package com.controller;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }


    @GetMapping("admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("custom-login")
    public String customLogin() {
        return "customLogin";
    }

    @GetMapping("custom-logout")
    public String customLogout(HttpServletRequest req, HttpServletResponse res) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(req, res, auth);
        }
        return "redirect:/home";
    }

    @RequestMapping("loginFailed")
    public String loginFailed(HttpServletRequest req, String message, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("result", "");
        return "customLogin";
    }

    @GetMapping("register")
    public String showFormRegister(HttpSession session) {
            return "register";
    }

    @PostMapping("register")
    public String register(String username, String password, Model model) {
        model.addAttribute("result", "");
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            model.addAttribute("message", "Username has been existed!");
        } else {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            this.userRepository.save(user);
            model.addAttribute("message", "Register Successfully!");
        }
        return "register";
    }

}
