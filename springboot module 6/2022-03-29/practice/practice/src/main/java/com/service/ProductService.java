package com.service;

import com.domain.Product;

import java.util.List;

public interface ProductService {
    int sumPrice();

    Product findById(Long id);

    List<Product> findAll();

    Product add(Product p);

    Product update(Product p);

    boolean deleteById(Long id);
}
