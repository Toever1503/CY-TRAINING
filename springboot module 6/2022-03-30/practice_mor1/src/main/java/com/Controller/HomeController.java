package com.Controller;

import com.domain.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("numbers", new int[]{1, 2, 3});
        model.addAttribute("name", "Join Psa");
        return "home";
    }

    @GetMapping("/add-role")
    public String addRole(Model model) {
        model.addAttribute("role", new Role());
        return "addRole";
    }

    @PostMapping("/add-role")
    public String handleAddUser(Role role, RedirectAttributes redirectAttributes) {

        System.out.println(role);
        redirectAttributes.addFlashAttribute("message", "Role added successfully");
        return "redirect:add-role";
    }

}
