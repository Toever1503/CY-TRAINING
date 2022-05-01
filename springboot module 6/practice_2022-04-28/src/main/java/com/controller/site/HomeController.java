package com.controller.site;

import com.entity.dto.CustomResponseEntity;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("latestProduct", productService.findAll(PageRequest.of(0, 4)));
        return "site/home";
    }

    @GetMapping("/api/products")
    @ResponseBody
    public Object getProduct(@RequestParam(name = "category", required = false, defaultValue = "0") Long categoryId,
                             @RequestParam Integer page) {
        if (categoryId == 0) {
            return CustomResponseEntity.of("", productService.findAll(PageRequest.of(page, 4)));
        }
        return CustomResponseEntity.of("", productService.findAllByCategory(categoryId, PageRequest.of(page, 4)));
    }
}
