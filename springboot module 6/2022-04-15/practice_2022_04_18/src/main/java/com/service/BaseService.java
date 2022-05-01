package com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<M, D, K> {
    D saveOrUpdate(M d);

    D findById(K id);

    boolean deleteById(K id);

    Page<D> findAll(Pageable pageable);

    Page<D> search(String search, Pageable pageable);
}
