package com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IBaseService<T, T1, K> {
    T1 save(T1 obj);

    T findById(K id);

    Page<T> findAll(Pageable page);

    boolean deleteById(K id);
}
