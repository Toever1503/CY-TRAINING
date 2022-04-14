package com.service;

import com.domain.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);

    boolean deleteById(Long id);

    Category updateCategory(Category category);

    Category findById(Long id);

    List<Category> findAll();

    List<Category> findAllByParent(Long id);
    List<Category> searchCat(String catName);

    Category findBySlug(String catSlug);


}
