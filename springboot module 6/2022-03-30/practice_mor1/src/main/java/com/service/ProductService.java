package com.service;

import com.domain.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    Product getProductById(Long id);

    void deleteProductBy(Long id);

    Product updateProduct(Product product);

    List<Product> getAllProducts();
}
