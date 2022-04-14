package com.service;

import com.entity.dto.ProductDTO;
import com.entity.model.ProductMODEL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IBaseService<ProductMODEL, ProductDTO, Long> {

    Page<ProductMODEL> filter(String q, Float minPrice, Float maxPrice, Long catId, Pageable page);
    Page<ProductDTO> findAllByCategoryId(Long id, Pageable pageable);

    Page<ProductDTO> search(String q, Pageable pageable);
}
