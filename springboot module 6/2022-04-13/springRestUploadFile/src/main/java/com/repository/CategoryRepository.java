package com.repository;

import com.domain.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

    Page<Categories> findAll(Pageable page);

    List<Categories> findAllByCatNameLike(String catName);

    Optional<Categories> findByCatSlug(String catSlug);

    List<Categories> findAllByCatParentId(Long id);

    List<Categories> findAllByIdIn(List<Long> id);

    @Query("select c from Categories c where c.catParent is null")
    List<Categories> getMenus();
}
