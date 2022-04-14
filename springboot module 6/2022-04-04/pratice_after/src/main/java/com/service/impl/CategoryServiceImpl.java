package com.service.impl;

import com.domain.Categories;
import com.repository.CategoryRepository;
import com.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;

    // sử dụng tạm thời list category thay cho việc tạo repository
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Categories addCategory(Categories category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == 1) return false;
        this.categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Categories updateCategory(Categories category) {
        if (category.getId() == 1) return null;
        return this.categoryRepository.save(category);
    }

    @Override
    public Categories findById(Long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Categories> findAll(Pageable page) {
        return this.categoryRepository.findAll(page);
    }

    @Override
    public List<Categories> findAllByParent(Long id) { // tìm kiếm danh sách category con bằng category cha id
        if (id == null) return Collections.EMPTY_LIST;
        return this.categoryRepository.findAllByCatParent(id);
    }

    @Override
    public List<Categories> searchCat(String catName) {
        return this.categoryRepository.findAllByCatNameLike(catName);
    }

    @Override
    public Categories findBySlug(String catSlug) {
        return this.categoryRepository.findByCatSlug(catSlug).orElse(null);
    }
}
