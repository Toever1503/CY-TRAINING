package com.repository;

import java.util.List;

public interface IRepository<O, K> {
    List<O> findAll();

    O findById(K id);
}
