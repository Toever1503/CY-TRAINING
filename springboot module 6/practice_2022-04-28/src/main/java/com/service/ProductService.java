package com.service;

import com.entity.dto.ProductDto;
import com.entity.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends BaseService<ProductModel, ProductDto, Long> {
    public Page<ProductDto> findAllByCategory(Long categoryId, Pageable page);
}
