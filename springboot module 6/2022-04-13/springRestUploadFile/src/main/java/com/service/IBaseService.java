package com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBaseService<D, M, K> {
    M save(M m);

    M update(M m);

    void delete(K k);

    D find(K k);

    Page<D> findAll(Pageable pageable);
}
