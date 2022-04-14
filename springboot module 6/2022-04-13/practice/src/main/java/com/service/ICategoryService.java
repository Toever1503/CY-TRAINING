package com.service;

import com.entity.dto.CategoryDTO;
import com.entity.model.CategoryMODEL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService extends IBaseService<CategoryMODEL, CategoryDTO, Long> {
    Page<CategoryDTO> findAllByCatParent(Long id, Pageable page);
}
