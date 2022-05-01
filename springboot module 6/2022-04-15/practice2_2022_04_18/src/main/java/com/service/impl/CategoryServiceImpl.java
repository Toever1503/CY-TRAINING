package com.service.impl;

import com.entity.Category;
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

    Category modelToEntity(CategoryModel categoryModel) {
        if (categoryModel == null) return null;
        return Category.builder()
                .id(categoryModel.getId())
                .catName(categoryModel.getCatName())
                .build();
    }

    CategoryDto toDto(Category category) {
        if (category == null) return null;
        return CategoryDto.builder()
                .id(category.getId())
                .catName(category.getCatName())
                .catParent(category.getCatParent() != null ? category.getCatParent().getCatName() : null)
                .build();
    }

    CategoryModel toModel(Category category) {
        if (category == null) return null;
        return CategoryModel.builder()
                .id(category.getId())
                .catName(category.getCatName())
                .catParent(category.getCatParent() != null ? category.getCatParent().getId() : null)
                .build();
    }

    @Override
    public CategoryDto saveOrUpdate(CategoryModel categoryModel) {
        Category entity = modelToEntity(categoryModel);
        if (entity.getId() != null) {
            Category oldCategory = categoryRepository.findById(entity.getId()).orElse(null);
            if (categoryModel.getCatParent() == null)
                entity.setCatParent(oldCategory.getCatParent());
        }
        return toDto(categoryRepository.save(entity));
    }

    @Override
    public CategoryDto findById(Long aLong) {
        return toDto(this.categoryRepository.findById(aLong).orElse(null));
    }

    @Override
    public boolean deleteBYId(Long aLong) {
        this.categoryRepository.deleteById(aLong);
        return true;
    }

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return this.categoryRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public Page<CategoryDto> search(String q, Pageable pageable) {
        return this.categoryRepository.findAllByCatNameLike("%" + q + "%", pageable).map(this::toDto);
    }
}
