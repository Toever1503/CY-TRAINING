package com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<M, D, ID> {
    D findById(ID id);

    Page<D> findAll(Pageable page);

    Page<D> search(String keyword, Pageable page);

    D add(M m);

    D update(M m);

    Boolean deleteById(ID id);
}
