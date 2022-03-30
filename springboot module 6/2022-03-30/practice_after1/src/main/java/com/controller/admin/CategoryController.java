package com.controller.admin;

import com.domain.Category;
import com.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showCategories(Model model) {
        model.addAttribute("listCats", categoryService.findAll());
        return "admin/categories";
    }

    @GetMapping("/add")
    public String showAddCategory(Model model) {
        model.addAttribute("listCats", categoryService.findAll());
        model.addAttribute("category", new Category());
        return "admin/addCategory";
    }

    @PostMapping
    public String handleAddCategory(Category category, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        Long catParent = Long.parseLong(req.getParameter("catParentId"));
        category.setCatParent(categoryService.findById(catParent));
        category.setStatus(true);
        category.setCatSlug(category.getCatName().toLowerCase().replace(" ", "-"));

        categoryService.addCategory(category);
        redirectAttributes.addFlashAttribute("message", "Category added successfully");
        return "redirect:/admin/categories/add";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategory(@PathVariable("id") Long id, Model model) {
        Category cat = categoryService.findById(id);
        model.addAttribute("catParentId", cat.getCatParent() != null ? cat.getCatParent().getId() : null);
        model.addAttribute("category", cat);
        model.addAttribute("listCats", categoryService.findAll().stream().filter(iCat -> iCat.getCatParent() == null).collect(Collectors.toList()));
        return "admin/editCategory";
    }

    @PostMapping("/edit/{id}")
    public String handleEditCategory(@PathVariable("id") Long id, Category category, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        Category cat = categoryService.findById(id);
        category.setId(id);
        category.setStatus(cat.getStatus());

        Long catParent = Long.parseLong(req.getParameter("catParentId"));
        if (catParent != 0)
            category.setCatParent(categoryService.findById(catParent));
        category.setCatSlug(category.getCatName().toLowerCase().replace(" ", "-"));
        categoryService.updateCategory(category);

        redirectAttributes.addFlashAttribute("message", "Category added successfully");
        return "redirect:/admin/categories/edit/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Category deleted successfully");
        return "redirect:/admin/categories";
    }

    @GetMapping("/disable/{id}")
    public String disableCategory(@PathVariable("id") Long id, @RequestParam("status") String status, RedirectAttributes redirectAttributes) {
        Category cat = categoryService.findById(id);
        cat.setStatus(status.equalsIgnoreCase("active") ? true : false);
        redirectAttributes.addFlashAttribute("message", "Category " + status + " successfully");
        return "redirect:/admin/categories";
    }
}
