package com.resource;

import com.entity.dto.ResponseData;
import com.entity.model.CategoryModel;
import com.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity getAllCategories(@RequestParam(name = "q", required = false, defaultValue = "") String q, Pageable page) {
        if (q != null || !q.isBlank()) {
            return ResponseData.get(categoryService.search(q, page));
        }
        return ResponseData.get(categoryService.findAll(page));
    }
    @GetMapping("{id}")
    public ResponseEntity getCategoryById(@PathVariable("id") Long id) {
        return ResponseData.get(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryModel model) {
        return ResponseData.update(categoryService.saveOrUpdate(model));
    }

    @PatchMapping("{id}")
    public ResponseEntity updateCategory(@PathVariable("id") Long id, @RequestBody CategoryModel model) {
        model.setId(id);
        return ResponseData.update(categoryService.saveOrUpdate(model));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id) {
        return ResponseData.delete(categoryService.deleteBYId(id));
    }
}
