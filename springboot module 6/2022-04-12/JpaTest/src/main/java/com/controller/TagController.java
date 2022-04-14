package com.controller;

import com.entities.Tag;
import com.entities.Video;
import com.repository.TagRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("anime/tag")
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    @GetMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @GetMapping
    public Object getAll(Pageable page) {
        return tagRepository.findAll(page);
    }

    @PatchMapping("{id}")
    public Object update(@PathVariable Long id, @RequestBody Tag video) {
        video.setId(id);
        return this.tagRepository.save(video);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Long id) {
        this.tagRepository.deleteById(id);
        return true;
    }
}
