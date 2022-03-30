package com.repository;

import java.util.List;

public interface IRepository<O, K> {
    O add(O o);

    O update(O o);

    O findById(K id);

    List<O> findAll();

    boolean deleteById(K id);
}
