package com.resource;

import com.entity.dto.ResponseData;
import com.entity.model.CategoryModel;
import com.entity.model.ProductModel;
import com.service.CategoryService;
import com.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity getAllProducts(@RequestParam(name = "q", required = false, defaultValue = "") String q, Pageable page) {
        if (q != null || !q.isBlank()) {
            return ResponseData.get(productService.search(q, page));
        }
        return ResponseData.get(productService.findAll(page));
    }

    @GetMapping("{id}")
    public ResponseEntity getProductsById(@PathVariable("id") Long id) {
        return ResponseData.get(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity createProducts(@RequestBody ProductModel model) {
        return ResponseData.update(productService.saveOrUpdate(model));
    }

    @PatchMapping("{id}")
    public ResponseEntity updateProducts(@PathVariable("id") Long id, @RequestBody ProductModel model) {
        model.setId(id);
        return ResponseData.update(productService.saveOrUpdate(model));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProducts(@PathVariable("id") Long id) {
        return ResponseData.delete(productService.deleteBYId(id));
    }
}
