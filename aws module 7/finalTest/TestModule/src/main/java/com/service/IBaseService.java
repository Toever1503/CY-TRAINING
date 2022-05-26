package com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBaseService<E, M, K> {
    E findById(K k);

    Page<E> findAll(Pageable page);
    Page<E> search(String q, Pageable page);

    List<E> findAll();

    E add(M m);

    E update(M m);

    boolean deleteById(K k);

    boolean deleteAllByIds(List<K> ks);

}
