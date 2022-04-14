package com.service;

import com.domain.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Categories addCategory(Categories category);

    boolean deleteById(Long id);

    Categories updateCategory(Categories category);

    Categories findById(Long id);

    Page<Categories> findAll(Pageable page);

    List<Categories> findAllByParent(Long id);
    List<Categories> searchCat(String catName);

    Categories findBySlug(String catSlug);


}
