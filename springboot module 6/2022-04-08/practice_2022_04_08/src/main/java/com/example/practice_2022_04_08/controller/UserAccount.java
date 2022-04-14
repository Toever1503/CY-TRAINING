package com.example.practice_2022_04_08.controller;

import com.example.practice_2022_04_08.domain.Adminstrator;
import com.example.practice_2022_04_08.domain.Student;
import com.example.practice_2022_04_08.domain.UserLogin;
import com.example.practice_2022_04_08.repository.AdministratorRepository;
import com.example.practice_2022_04_08.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UserAccount {

    private final AdministratorRepository administratorRepository;
    private final StudentRepository studentRepository;

    public UserAccount(AdministratorRepository administratorRepository, StudentRepository studentRepository) {
        this.administratorRepository = administratorRepository;
        this.studentRepository = studentRepository;
    }


    @GetMapping("login")
    public String login(Model model, @RequestParam(name = "force", required = false, defaultValue = "0") Boolean force, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("userLogin");
        if (userLogin != null && !force)
            return "redirect:/";
        model.addAttribute("user", new UserLogin());
        model.addAttribute("pageTitle", "Login");
        return "login";
    }

    @PostMapping("login")
    public String hangleLogin(UserLogin user, HttpSession session, @RequestParam(name = "type") String type, @RequestParam(name = "redirect", required = false, defaultValue = "/") String redirect, RedirectAttributes redirectAttributes) {
        Student studentUser = null;
        Adminstrator adminUser = null;
        UserLogin checkedUser = null;

        // phân loại người dùng đăng nhập và set lên session
        if (type.equals("student")) {
            studentUser = this.studentRepository.findByUsername(user.getUsername()).orElse(null);
            if (studentUser != null)
                checkedUser = new UserLogin(studentUser.getId(), studentUser.getUsername(), studentUser.getPassword(), studentUser.getStudentName());
        } else {
            adminUser = this.administratorRepository.findByUsername(user.getUsername()).orElse(null);
            if (adminUser != null)
                checkedUser = new UserLogin(adminUser.getId(), adminUser.getUsername(), adminUser.getPassword(), adminUser.getFullname());
        }

        String toastType = "error";
        if (checkedUser == null) {
            redirectAttributes.addFlashAttribute("message", "Username or password is incorrect");
            redirect = "/login";
        } else {
            if (user.getPassword().equals(checkedUser.getPassword())) {
                // phân loại người dùng đăng nhập và set lên session
                if (type.equals("admin"))
                    session.setAttribute("adminstratorLogin", checkedUser);
                else
                    session.setAttribute("studentLogin", checkedUser);
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
