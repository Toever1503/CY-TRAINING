package com.service;

import com.domain.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    Product findById(Long id);

    Product updateProduct(Product product);

    boolean deleteById(Long id);

    List<Product> findAll(Integer page);
    List<Product> searchProduct(String q);

    List<Product> findAllByCategory(Long catId);
}
