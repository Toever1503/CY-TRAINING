package com.service;

import com.domain.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static Long increment = 1l;

    private List<Category> categories;

    public CategoryServiceImpl() {
        categories = new ArrayList<Category>();
        categories.add(new Category(increment++, "Cat 1", "cat-1", 1, null, true));
        categories.add(new Category(increment++, "Cat 2", "cat-2", 2, null, true));
        categories.add(new Category(increment++, "Cat 3", "cat-3", 3, null, false));
        categories.add(new Category(increment++, "Cat 4", "cat-4", 4, null, true));
        categories.add(new Category(increment++, "Cat 5", "cat-5", 5, null, false));
        categories.add(new Category(increment++, "Cat 6", "cat-6", 6, null, true));

        categories.add(new Category(increment++, "Cat 7", "cat-7", 3, categories.get(0), true));
        categories.add(new Category(increment++, "Cat 8", "cat-8", 4, categories.get(0), true));
        System.out.println("CategoryServiceImpl size: " + categories.size());
    }

    @Override
    public Category addCategory(Category category) {
        category.setId(increment++);
        this.categories.add(category);
        return category;
    }

    @Override
    public boolean deleteById(Long id) {
        return this.categories.removeIf(category -> category.getId().equals(id));
    }

    @Override
    public Category updateCategory(Category category) {
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
    public List<Category> findAllByParent(Long id) {
        if(id == null) return Collections.EMPTY_LIST;
        return this.categories.stream().filter(catRoot -> catRoot.getCatParent() != null)
                .filter(cat -> cat.getCatParent().getId().equals(id))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println("static increment-> "+ CategoryServiceImpl.increment);

    }
}
