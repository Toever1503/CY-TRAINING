package com.repository;

import com.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c join Videoandcategory vc on c.id = vc.id.category where vc.id.video = ?1")
    Page<Category> findAllCategoriesByVideoId(Long id, Pageable pageable);

    @Query("select c from Category c join Videoandcategory vc on c.id = vc.id.category where c.name like %?1% and vc.id.video = ?2")
    Page<Category> searchByAndVideoId(Object q, Long id, Pageable pageable);
}