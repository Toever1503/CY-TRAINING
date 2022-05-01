package com.service.impl;

import com.entity.CategoryEntity;
import com.entity.dto.CategoryDto;
import com.entity.model.CategoryModel;
import com.repository.CategoryRepository;
import com.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    CategoryEntity toEntity(CategoryModel categoryModel) {
        if (categoryModel == null) return null;
        return new CategoryEntity(categoryModel.getId(), categoryModel.getName());
    }

    @Override
    public CategoryDto findById(Long aLong) {
        return CategoryDto.toDto(categoryRepository.findById(aLong).orElse(null));
    }

    @Override
    public Page<CategoryDto> findAll(Pageable page) {
        return this.categoryRepository.findAll(page).map(CategoryDto::toDto);
    }

    @Override
    public Page<CategoryDto> search(String keyword, Pageable page) {
        return this.categoryRepository.findAllByNameLike("%" + keyword + "%", page).map(CategoryDto::toDto);
    }

    @Override
    public CategoryDto add(CategoryModel categoryModel) {
        return CategoryDto.toDto(this.categoryRepository.save(toEntity(categoryModel)));
    }

    @Override
    public CategoryDto update(CategoryModel categoryModel) {
        return CategoryDto.toDto(this.categoryRepository.save(toEntity(categoryModel)));
    }

    @Override
    public Boolean deleteById(Long integer) {
        this.categoryRepository.deleteById(integer);
        return true;
    }

}
