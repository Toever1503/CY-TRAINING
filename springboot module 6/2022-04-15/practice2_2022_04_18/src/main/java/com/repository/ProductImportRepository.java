package com.repository;

import com.entity.ParcelImportDetail;
import com.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImportRepository extends JpaRepository<ParcelImportDetail, Long> {
}
