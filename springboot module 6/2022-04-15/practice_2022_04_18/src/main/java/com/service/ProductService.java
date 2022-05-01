package com.service;

import com.domain.dto.ProductDto;
import com.domain.model.ProductModel;
import org.springframework.stereotype.Service;

@Service
public interface ProductService extends BaseService<ProductModel, ProductDto, Long> {
}
