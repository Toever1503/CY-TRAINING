package com.controller;

import com.repository.CharRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("anime/char")
public class CharController {

    private final CharRepository charRepository;

    public CharController(CharRepository charRepository) {
        this.charRepository = charRepository;
    }

    @GetMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return charRepository.findById(id).orElse(null);
    }
}
