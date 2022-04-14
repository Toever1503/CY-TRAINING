package com.repository;

import com.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByIdIn(List<Long> catChilds);

    @Query("select c from Category c where c.catParent.id = ?1")
    Page<Category> getAllCatByParentID(Long id, Pageable pageable);


    Page<Category> findAllByCatParentIsNull(Pageable pageable);


}
