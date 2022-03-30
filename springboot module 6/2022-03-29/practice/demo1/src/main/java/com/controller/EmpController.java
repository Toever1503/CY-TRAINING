package com.controller;

import com.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmpController {
    private final EmployeeService employeeService;

    public EmpController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("{id}")
    public Object findById(@PathVariable("id") Long id) {
        return this.employeeService.findById(id);
    }

    @GetMapping("")
    public Object findAll() {
        return this.employeeService.findAll();
    }
}
