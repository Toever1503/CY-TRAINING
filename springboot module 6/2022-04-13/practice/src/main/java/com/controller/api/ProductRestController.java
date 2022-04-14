package com.controller.api;

import com.entity.dto.ProductFilterDTO;
import com.entity.model.ProductMODEL;
import com.service.ICategoryService;
import com.service.IProductService;
import com.util.ImageUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductRestController {

    private final IProductService productService;
    private final ICategoryService categoryService;

    public ProductRestController(IProductService productService, ICategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public Object getProducts(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping("{id}")
    public Object getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/category/{id}")
    public Object getProductsByCategory(@PathVariable Long id, Pageable pageable) {
        return this.productService.findAllByCategoryId(id, pageable);
    }

    @PostMapping
    public Object create(ProductMODEL product, @RequestPart(required = false) MultipartFile file) {
        product.setId(null);
        String image = ImageUtil.uploadImage(file);
        if (image != null)
            product.setImage("http://localhost:8080/images/" + image);
        return this.productService.save(product);
    }

    @PutMapping("{id}")
    public Object update(@PathVariable Long id, ProductMODEL product, @RequestPart(required = false) MultipartFile file) {
        product.setId(id);
        String image = ImageUtil.uploadImage(file);
        if (image != null)
            product.setImage("http://localhost:8080/images/" + image);
        return this.productService.save(product);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Long id) {
        return this.productService.deleteById(id);
    }

    @GetMapping("search")
    public Object search(@RequestParam(name = "q") String q, Pageable pageable) {
        return this.productService.search(q, pageable);
    }

    @GetMapping("filter")
    public Object filter(ProductFilterDTO filter, Pageable pageable) {
//        return this.productService.search(q, pageable);
        return null;
    }
}
