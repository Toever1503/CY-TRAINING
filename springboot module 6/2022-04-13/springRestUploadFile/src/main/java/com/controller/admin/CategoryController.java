package com.controller.admin;

import com.domain.model.CategoryModel;
import com.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("admin/categories") // path default cho controller
public class CategoryController {

    // autowired bằng constructor
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Object create(@RequestBody CategoryModel categoryModel) {
        categoryModel.setId(null);
        return this.categoryService.save(categoryModel);
    }

    @PatchMapping("{id}")
    public Object update(@PathVariable Long id, @RequestBody CategoryModel model) {
        model.setId(id);
        return this.categoryService.update(model);
    }

    @GetMapping
    public Object findAll(Pageable page){
        return this.categoryService.findAll(page);
    }

    @GetMapping("{id}")
    public Object getCategory(@PathVariable Long id){
        return this.categoryService.find(id);
    }
}
