package com.service;

import com.domain.People;

import java.util.List;

public interface PeopleService {
    People findById(Long id);

    List<People> findAll(String sortBy);

    People add(People p);

    People update(People p);

    boolean deleteById(Long id);
}
