package com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBaseService<M, D, K> {
    M save(M m);

    M update(M m);

    D findById(K k);

    boolean deleteById(K k);

    Page<D> findAll(Pageable pageable);
}
