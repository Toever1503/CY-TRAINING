package com.repository;

import com.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findAllByNameLike(String keyword, Pageable pageable);

    Page<ProductEntity> findAllByCategoryId(Long categoryId, Pageable pageable);
}
