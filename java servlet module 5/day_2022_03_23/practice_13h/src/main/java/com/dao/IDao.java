package com.dao;

import java.util.List;

public interface IDao<T, K> {
    T save(T obj);

    T update(T obj);

    boolean deleteById(K id);

    T findById(K id);

    List<T> findAll();
}
