package com.service.impl;

import com.domain.Category;
import com.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static Long increment = 1l;
    private List<Category> categories;
    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    // sử dụng tạm thời list category thay cho việc tạo repository
    public CategoryServiceImpl() {
        categories = new ArrayList<Category>();
        categories.add(new Category(increment++, "Default", "default", 1, null, false));
        categories.add(new Category(increment++, "Latest", "latest", 3, null, true));
        categories.add(new Category(increment++, "Spring 2022", "spring-2022", 1, null, true));
        categories.add(new Category(increment++, "Summer 2022", "summer-2022", 2, null, true));
        categories.add(new Category(increment++, "Winter 2022", "cat-4", 4, null, true));

        categories.add(new Category(increment++, "T-Shirt", "t-shirt", 3, categories.get(0), true));
        categories.add(new Category(increment++, "Jacket", "jacket", 4, categories.get(2), true));
        categories.add(new Category(increment++, "Hoodie", "hoodie", 4, categories.get(2), true));
        categories.add(new Category(increment++, "Shoes", "shoes", 4, categories.get(0), true));
        categories.add(new Category(increment++, "Bag & Accessories", "bag-and-accessories", 4, categories.get(1), true));
        categories.add(new Category(increment++, "Sandle", "sandle", 4, categories.get(1), true));
        log.info("CategoryServiceImpl size: " + categories.size());
    }

    @Override
    public Category addCategory(Category category) {
        category.setId(increment++);
        this.categories.add(category);
        return category;
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == 1) return false;
        return this.categories.removeIf(category -> category.getId().equals(id));
    }

    @Override
    public Category updateCategory(Category category) {
        if(category.getId() == 1) return null;
        this.categories = this.categories.stream().map(c -> c.getId().equals(category.getId()) ? category : c).collect(Collectors.toList());
        return category;
    }

    @Override
    public Category findById(Long id) {
        return this.categories.stream().filter(category -> category.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return this.categories;
    }

    @Override
    public List<Category> findAllByParent(Long id) { // tìm kiếm danh sách category con bằng category cha id
        if (id == null) return Collections.EMPTY_LIST;
        return this.categories.stream().filter(catRoot -> catRoot.getCatParent() != null)
                .filter(cat -> cat.getCatParent().getId().equals(id) && cat.getId() != 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> searchCat(String catName) {
        return this.categories.stream().filter(category -> category.getCatName().contains(catName)).collect(Collectors.toList());
    }

    @Override
    public Category findBySlug(String catSlug) {
        return this.categories.stream().filter(category -> category.getCatSlug().equals(catSlug) &&  category.getId() != 1).findFirst().orElse(null);
    }
}
