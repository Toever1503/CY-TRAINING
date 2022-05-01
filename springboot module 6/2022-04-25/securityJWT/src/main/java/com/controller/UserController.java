package com.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<?> dashboard() {
        return ResponseEntity.ok("users dashboard");
    }
}
