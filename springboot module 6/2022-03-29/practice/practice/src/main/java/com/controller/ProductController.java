package com.controller;

import com.domain.Product;
import com.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Object getProducts() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public Object getProduct(@PathVariable("id") Long id) {
        return this.productService.findById(id);
    }

    @PostMapping
    public Object addProduct(@RequestBody Product p) {
        if (p.getId() == null) return null;
        return this.productService.add(p);
    }

    @PatchMapping("/{id}")
    public Object updateProduct(@PathVariable("id") Long id, @RequestBody Product p) {
        p.setId(id);
        return this.productService.update(p);
    }

    @DeleteMapping("/{id}")
    public Object deleteProduct(@PathVariable("id") Long id) {
        return this.productService.deleteById(id);
    }

    @GetMapping("sumTotal")
    public Object sumTotalPricesOfProduct() {
        return this.productService.sumPrice();
    }
}
