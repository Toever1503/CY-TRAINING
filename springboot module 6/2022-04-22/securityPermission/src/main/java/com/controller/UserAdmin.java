package com.controller;

import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class UserAdmin {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EDITOR')")
    public String delete(Long id, Model model) {
        String message = "User cannot be deleted!";
        try {
            userRepository.deleteById(id);
            message = "User deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("message", message);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(Long id) {
        return "admin/edit";
    }

}
