package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> dashboard(){
        return ResponseEntity.ok("admin dashboard");
    }
}
