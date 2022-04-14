package com.controller;

import com.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("anime/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("{id}")
    public Object getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

//    @PatchMapping("{id}")
//    public Object get
}
