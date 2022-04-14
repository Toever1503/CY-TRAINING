package com.controller;

import com.domain.User;
import com.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@Controller
@RequestMapping("users")
public class homeController {

    private final UserRepository userRepository;
    private final EntityManager em;

    public homeController(UserRepository userRepository, EntityManager em) {
        this.userRepository = userRepository;
        this.em = em;
    }

    @GetMapping("test")
    @ResponseBody
    public Object test() {
        User u=em.find(User.class, 1l);

        u.setId(999l);
        return u;
    }

    @ResponseBody
    @GetMapping("{id}")
    public Object findUserById(@PathVariable Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @ResponseBody
    @GetMapping
    public Object findAllUsers(Pageable page) {
        return this.userRepository.findAll(page);
    }

    @ResponseBody
    @PostMapping
    public Object addUser(User user) {
        user.setId(null);
        return this.userRepository.save(user);
    }

    @ResponseBody
    @PostMapping("/edit/{id}")
    public Object editUser(@PathVariable Long id, User user) {
        user.setId(id);
        return this.userRepository.save(user);
    }

}
