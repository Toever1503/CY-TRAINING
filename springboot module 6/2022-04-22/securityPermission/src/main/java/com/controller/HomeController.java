package com.controller;

import com.entity.Role;
import com.entity.User;
import com.entity.model.UserLogin;
import com.entity.model.UserRegister;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index() {
        return "home";
    }

    @RequestMapping("/403")
    public String error403() {
        return "403";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
//            session.removeAttribute("login_message");
        return "login_page";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(UserRegister user, Model model) {
        Role role = this.roleRepository.findByRoleName(Role.USER);

        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());

        if (role != null)
            u.setUserRoles(Set.of(role));
        if (this.userRepository.save(u) != null)
            model.addAttribute("message", "Register Successfully!");
        else
            model.addAttribute("message", "Register Failed!");
        return "register";
    }


}
