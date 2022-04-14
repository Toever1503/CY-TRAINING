package com.dao;

import com.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepositoryDataJDBC extends CrudRepository<Person, Long> {
    Optional<Person> findByName(String name);

    Page<Person> findAll(Pageable page);
}
