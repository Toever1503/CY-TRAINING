package com.animes.controller;

import com.animes.repository.CharRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("anime/char")
public class CharController {

    private final CharRepository charRepository;

    public CharController(CharRepository charRepository) {
        this.charRepository = charRepository;
    }

}
