package com.service;

import com.domain.Product;
import com.service.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductDto findById(Long id);

    ProductDto save(ProductDto obj);

    boolean deleteById(Long id);

    Page<ProductDto> findAll(Pageable page);

    List<ProductDto> search(String q);

    List<Product> findAllByCategory(Long id);
}
