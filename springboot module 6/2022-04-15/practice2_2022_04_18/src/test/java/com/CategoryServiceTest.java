package com;

import com.entity.Category;
import com.repository.CategoryRepository;
import com.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@SpringBootTest
@DataJpaTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testFindByIdNotExist() {
        Assert.isNull(this.categoryService.findById(1l), "Find Category with id 1 is null");
    }

    @Test
    public void testFindByIdExist() {
        Assert.isNull(this.categoryService.findById(2l), "Find Category with id 1 is null");
    }

    @Test
    public void testFindAllEmpty() {
        Assert.notEmpty(this.categoryService.findAll(PageRequest.of(0, 1)).getContent(), "Find all empty");
    }

    @Test
    public void testFindAllHasElement() {
        org.junit.Assert.assertEquals(2, this.categoryService.findAll(PageRequest.of(0, 2)).getTotalElements());
    }

    @Test
    public void testInsertSuccess(){
//        Assert.notNull(categoryRepository.save(new Category(null, "cat test", null)), "Insert success");
        System.out.println(categoryRepository.findAll().isEmpty());
    }

}
