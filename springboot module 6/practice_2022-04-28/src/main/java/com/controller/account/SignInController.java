package com.controller.account;

import com.entity.dto.UserLoginRequest;
import com.entity.dto.UserSignupRequest;
import com.entity.model.UserModel;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpProcesing(UserSignupRequest userSignup, Model model) {
        System.out.println(userSignup);

        UserModel userModel = UserModel.builder()
                .username(userSignup.getUsername())
                .password(userSignup.getPassword())
                .email(userSignup.getEmail())
                .timeFailed(0)
                .status(true)
                .build();

        model.addAttribute("result", "error");
        model.addAttribute("message", "Sign up new failed!");
        if(userService.add(userModel) != null) {
            model.addAttribute("result", "success");
            model.addAttribute("message", "Sign up successfully!");
        }
        return "signup";
    }

    @GetMapping("/signin")
    public String signIn() {
        return "signin";
    }

    @PostMapping("/signin")
    public String signInProcessing(UserLoginRequest userLogin) {
        System.out.println(userLogin);
        return "redirect:/";
    }

}
