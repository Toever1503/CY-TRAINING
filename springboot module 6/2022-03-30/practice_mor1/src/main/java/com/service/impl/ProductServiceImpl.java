package com.service.impl;

import com.domain.Product;
import com.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private List<Product> products;

    public ProductServiceImpl() {
        products = new ArrayList<Product>();

        products.add(new Product(1L, "Product 1", 10, false));
        products.add(new Product(2L, "Product 2", 20, true));
        products.add(new Product(3L, "Product 3", 30, false));
        products.add(new Product(4L, "Product 4", 40, true));
        products.add(new Product(5L, "Product 5", 50, true));
        products.add(new Product(6L, "Product 6", 60, false));
        products.add(new Product(7L, "Product 7", 70, true));
        products.add(new Product(8L, "Product 8", 80, false));
        products.add(new Product(9L, "Product 9", 90, true));
        products.add(new Product(10L, "Product 10", 100, false));
    }

    @Override
    public Product addProduct(Product product) {
        this.products.add(product);
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return this.products.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void deleteProductBy(Long id) {
        this.products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public Product updateProduct(Product product) {
        this.products = this.products.stream().map(p -> p.getId().equals(product.getId()) ? product : p).collect(Collectors.toList());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.products;
    }
}
