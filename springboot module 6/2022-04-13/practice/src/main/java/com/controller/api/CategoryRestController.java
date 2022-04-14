package com.controller.api;

import com.entity.model.CategoryMODEL;
import com.service.ICategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryRestController implements Serializable {
    private final ICategoryService categoryService;

    public CategoryRestController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Object getCategoriess(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("parent/{id}")
    public Object getCategoriesByParent(@PathVariable Long id) {
        System.out.println("id:--> " + id);
        return categoryService.findAllByCatParent(id, PageRequest.of(0, 1000));
    }

    @GetMapping("parentNull")
    public Object getCategoriesByParentNull() {
        return categoryService.findAllByCatParent(null, PageRequest.of(0, 1000));
    }

    @GetMapping("{id}")
    public Object getCategory(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public Object create(@RequestBody CategoryMODEL model) {
        model.setId(null);
        return this.categoryService.save(model);
    }

    @CrossOrigin("*")
    @PutMapping("{id}")
    public Object update(@PathVariable Long id, @RequestBody CategoryMODEL model) {
        model.setId(id);
        return this.categoryService.save(model);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Long id) {
        return this.categoryService.deleteById(id);
    }


}
