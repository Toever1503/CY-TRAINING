package com.controller;

import com.repository.StaffRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("anime/staff")
public class Staff {
    private final StaffRepository staffRepository;

    public Staff(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    @GetMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return staffRepository.findById(id).orElse(null);
    }

}
