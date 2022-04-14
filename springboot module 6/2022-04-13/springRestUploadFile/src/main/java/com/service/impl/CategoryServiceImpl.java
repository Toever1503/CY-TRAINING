package com.service.impl;

import com.domain.Categories;
import com.domain.dto.CategoryDTO;
import com.domain.model.CategoryModel;
import com.repository.CategoryRepository;
import com.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;

    // sử dụng tạm thời list category thay cho việc tạo repository
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    Categories mToEntity(CategoryModel model) {
        return Categories.builder()
                .id(model.getId())
                .catName(model.getCatName())
                .catSlug(model.getCatSlug())
                .catOrder(model.getCatOrder())
                .status(model.getStatus())
                .build();
    }

    CategoryDTO toDto(Categories entity) {
        return CategoryDTO.builder()
                .id(entity.getId())
                .catName(entity.getCatName())
                .catSlug(entity.getCatSlug())
                .catOrder(entity.getCatOrder())
                .status(entity.getStatus())
                .catParent(entity.getCatParent())
                .subCategories(entity.getSubCategories())
                .build();
    }

    CategoryModel toModel(Categories entity) {
        return CategoryModel.builder()
                .id(entity.getId())
                .catName(entity.getCatName())
                .catSlug(entity.getCatSlug())
                .catOrder(entity.getCatOrder())
                .status(entity.getStatus())
                .catParent(entity.getCatParent() != null ? entity.getCatParent().getId() : null)
                .subCategories(entity.getSubCategories() != null ? entity.getSubCategories().stream().map(c -> c.getId()).collect(Collectors.toList()) : Collections.emptyList())
                .build();
    }

    @Override
    @Transactional
    public CategoryModel save(CategoryModel categoryModel) {
        Categories entity = mToEntity(categoryModel);
        entity.setCatParent(categoryRepository.findById(categoryModel.getCatParent()).orElse(null));
        entity.setSubCategories(this.categoryRepository.findAllByIdIn(categoryModel.getSubCategories()));
        return toModel(this.categoryRepository.save(entity));
    }

    @Override
    @Transactional
    public CategoryModel update(CategoryModel categoryModel) {
        return save(categoryModel);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CategoryDTO find(Long aLong) {
        return toDto(
                this.categoryRepository.findById(aLong).orElse(null)
        );
    }

    @Override
    @Transactional
    public Page<CategoryDTO> findAll(Pageable pageable) {
        return this.categoryRepository.findAll(pageable).map(this::toDto);
    }
}
