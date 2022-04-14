package com.service.impl;

import com.entity.Category;
import com.entity.dto.CategoryDTO;
import com.entity.model.CategoryMODEL;
import com.repository.CategoryRepository;
import com.repository.ProductRepository;
import com.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    Category toEntity(CategoryMODEL model) {
        if (model == null) return null;
        return Category.builder()
                .id(model.getId())
                .catName(model.getCatName())
                .slug(model.getSlug())
                .build();
    }

    CategoryMODEL entityToModel(Category entity) {
        if (entity == null) return null;
        return CategoryMODEL.builder()
                .id(entity.getId())
                .catName(entity.getCatName())
                .slug(entity.getSlug())
                .catParent(entity.getCatParent() != null ? entity.getCatParent().getId() : null)
                .catChilds(entity.getCatChilds() != null ? entity.getCatChilds().stream().map(Category::getId).collect(java.util.stream.Collectors.toList()) : null)
                .build();
    }

    CategoryDTO toDto(Category entity) {
        if (entity == null) return null;
        return CategoryDTO.builder()
                .id(entity.getId())
                .catName(entity.getCatName())
                .slug(entity.getSlug())
                .catParent(entity.getCatParent())
                .catChilds(entity.getCatChilds())
                .build();
    }

    @Override
    @Transactional
    public CategoryMODEL save(CategoryMODEL model) {
        Category parent = model.getCatParent() == null ? null : this.categoryRepository.findById(model.getCatParent()).orElse(null);
        List<Category> childs = model.getCatChilds() == null ? null : this.categoryRepository.findAllByIdIn(model.getCatChilds());

        Category entity = this.toEntity(model);
        if (model.getId() != null) {
            Category oldCatParent = this.categoryRepository.findById(model.getId()).orElse(null);
            entity.setCatChilds(oldCatParent.getCatChilds());
        }
        entity.setCatParent(parent);
        entity.setCatChilds(childs);

        return entityToModel(this.categoryRepository.save(entity));
    }

    @Override
    public CategoryMODEL update(CategoryMODEL categoryMODEL) {
        return save(categoryMODEL);
    }

    @Override
    @Transactional
    public CategoryDTO findById(Long aLong) {
        return toDto(this.categoryRepository.findById(aLong).orElse(null));
    }

    @Override
    public boolean deleteById(Long aLong) {
        this.categoryRepository.deleteById(aLong);
        return true;
    }

    @Override
    public Page<CategoryDTO> findAll(Pageable pageable) {
        return this.categoryRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public Page<CategoryDTO> findAllByCatParent(Long id, Pageable page) {
        if (id == null || id == 0) return this.categoryRepository.findAllByCatParentIsNull(page).map(this::toDto);
        return this.categoryRepository.getAllCatByParentID(id, page).map(this::toDto);
    }
}
