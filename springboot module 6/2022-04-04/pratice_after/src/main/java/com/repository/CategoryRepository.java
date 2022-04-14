package com.repository;

import com.domain.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Categories, Long> {

    Page<Categories> findAll(Pageable page);

    List<Categories> findAllByCatNameLike(String catName);

    Optional<Categories> findByCatSlug(String catSlug);

    List<Categories> findAllByCatParent(Long id);
}
