package com.service;

import com.domain.Categories;
import com.domain.dto.CategoryDTO;
import com.domain.model.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService extends IBaseService<CategoryDTO, CategoryModel, Long> {
}
