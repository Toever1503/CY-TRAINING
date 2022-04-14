package com.controller.site;

import com.domain.User;
import com.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

@Controller
public class UserAccount {

    private final UserService userService;
    public UserAccount(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String login(Model model, @RequestParam(name = "force", required = false, defaultValue = "0") Boolean force, HttpSession session) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin != null && !force)
            return "redirect:/";
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Login");
        return "login";
    }

    @PostMapping("login")
    public String handleLogin(User user, HttpSession session, @RequestParam(name = "redirect", required = false, defaultValue = "/") String redirect, RedirectAttributes redirectAttributes) {
        User checkUser = userService.findByUsername(user.getUsername());
        String toastType = "error";
        if (checkUser == null) {
            redirectAttributes.addFlashAttribute("message", "Username or password is incorrect");
            redirect = "/login";
        } else {
            if (user.getPassword().equals(checkUser.getPassword())) {
                session.setAttribute("userLogin", checkUser);
                redirectAttributes.addFlashAttribute("message", "Login Successful");
                toastType = "success";
            } else {
                redirectAttributes.addFlashAttribute("message", "Username or password is incorrect");
                redirect = "/login";
            }
        }

        redirectAttributes.addFlashAttribute("toastType", toastType);
        return "redirect:".concat(redirect);
    }

}
