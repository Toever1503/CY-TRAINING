package com.practice_2022_04_06.dao;

import java.util.List;

public interface Dao<T,K>{
    T findById(K id);
    T save(T entity);
    boolean delete(T entity);
    boolean deleteById(K id);
    List<T> findAll(int page, int size, String sortBy, String sortDirection);
    Long count();
}
