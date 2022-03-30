package com.repository;

import com.domain.People;

import java.util.List;

public interface PeopleRepository extends IRepository<People, Long> {
    List<People> findAll(String sortBy);
}
