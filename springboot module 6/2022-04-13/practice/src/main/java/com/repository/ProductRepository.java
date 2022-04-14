package com.repository;

import com.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.LongStream;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.title like ?1 or p.price between ?2 and ?3 or p.category.id = ?4")
    Page<Product> search(String q, Float minPrice, Float maxPrice, Long catId, Pageable page);

    Page<Product> findAllByCategoryId(Long id, Pageable pageable);
    Page<Product> findAllByTitleLike(String q, Pageable pageable);
}
