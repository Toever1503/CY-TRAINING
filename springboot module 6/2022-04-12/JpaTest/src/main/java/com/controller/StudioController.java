package com.controller;

import com.repository.StudioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("anime/studio")
public class StudioController {
    private final StudioRepository studioRepository;

    public StudioController(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }
    @GetMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return studioRepository.findById(id).orElse(null);
    }
}
