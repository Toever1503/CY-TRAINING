package com.controller.admin;

import com.domain.User;
import com.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUserList(@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model){
        model.addAttribute("listUsers", userService.findAll(PageRequest.of(page, 10)));
        return "admin/user/users";
    }

    @GetMapping("add")
    public String showAddUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/addUser";
    }

    @PostMapping
    public String handleAddUser(User user, RedirectAttributes redirectAttributes) {
        User checkUser = userService.findByUsername(user.getUsername());
        String toastType = "error";

        if(checkUser == null) {
            if(userService.save(user) != null)
            {
                toastType = "success";
                redirectAttributes.addFlashAttribute("message", "User added successfully");
            }
            else
                redirectAttributes.addFlashAttribute("message", "User add failed");
            redirectAttributes.addFlashAttribute("toastType", toastType);
            return "redirect:/admin/users";
        }
        else {
            redirectAttributes.addFlashAttribute("message", "User with this username already exists");
            redirectAttributes.addFlashAttribute("toastType", toastType);
            return "redirect:/admin/users/add";
        }
    }

    @GetMapping("edit/{id}")
    public String showEditUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/user/editUser";
    }

    @PostMapping("edit/{id}")
    public String handleEditUser(@PathVariable Long id, User user, RedirectAttributes redirectAttributes) {
        user.setId(id);
        User originalUser = userService.findById(id);
        String toastType = "error";
        if(user.getPassword().isEmpty())
            user.setPassword(originalUser.getPassword());

        if (userService.save(user) != null) {
            toastType = "success";
            redirectAttributes.addFlashAttribute("message", "User updated successfully");
        }
        else
            redirectAttributes.addFlashAttribute("message", "User update failed");

        redirectAttributes.addFlashAttribute("toastType", toastType);
        return "redirect:/admin/users/edit/" + id;
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String toastType = "error";
        if (userService.deleteById(id)) {
            toastType = "success";
            redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        }
        else
            redirectAttributes.addFlashAttribute("message", "User delete failed");
        redirectAttributes.addFlashAttribute("toastType", toastType); // set toastType để show modal thông báo
        return "redirect:/admin/users";
    }

    @GetMapping("search")
    public String searchUser(@RequestParam("q") String q, Model model) {
        List<User> searchUserList = userService.searchUser(q);
        model.addAttribute("pageTitle", "Search product"); // set tên hiển thị cho trang
        model.addAttribute("listUsers", searchUserList); // set list user tìm kiếm được để hiển thị
        if(searchUserList.isEmpty()){
            model.addAttribute("toastType", "error");
            model.addAttribute("message", "No user found");
        }
        return "admin/user/users";
    }
}
