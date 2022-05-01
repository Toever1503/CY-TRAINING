package com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<M, D, K> {
    D saveOrUpdate(M m);
    D findById(K k);
    boolean deleteBYId(K k);
    Page<D> findAll(Pageable pageable);
    Page<D> search(String q, Pageable pageable);
}
