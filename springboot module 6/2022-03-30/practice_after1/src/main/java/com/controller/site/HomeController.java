package com.controller.site;

import com.domain.Category;
import com.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String index(Model model) {
        Map<Category, List<Category>> menuList = new HashMap<>();
        List<Category> parentMenuList = categoryService.findAll().stream().filter(cat -> cat.getStatus() != false && cat.getCatParent() == null).collect(Collectors.toList());
        parentMenuList.sort((a, b) -> a.getCatOrder() - b.getCatOrder());

        parentMenuList.forEach(category -> {
            List<Category> subItem = categoryService.findAllByParent(category.getId());
            if (subItem != null) {
                subItem = subItem.stream().filter(subI -> subI.getStatus() != false).collect(Collectors.toList());
                subItem.sort((a, b) -> a.getCatOrder() - b.getCatOrder());
            }
            menuList.put(category, subItem);
        });

        model.addAttribute("menuList", menuList);
        return "index";
    }
}
