package com.animes.controller;

import com.animes.repository.StaffRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("anime/staff")
public class Staff {
    private final StaffRepository staffRepository;

    public Staff(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


}
