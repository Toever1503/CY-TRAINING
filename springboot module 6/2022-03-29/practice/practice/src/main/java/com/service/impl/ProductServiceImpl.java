package com.service.impl;

import com.domain.Product;
import com.repository.ProductRepository;
import com.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public int sumPrice() {
        return this.productRepository.sumTotalPricesOfProduct();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product add(Product p) {
        return this.productRepository.add(p);
    }

    @Override
    public Product update(Product p) {
        return (Product) this.productRepository.update(p);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.productRepository.deleteById(id);
    }
}
