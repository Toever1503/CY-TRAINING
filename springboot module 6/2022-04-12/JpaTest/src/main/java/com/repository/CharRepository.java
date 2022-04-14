package com.repository;

import com.entities.Chars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharRepository extends JpaRepository<Chars, Long> {
    List<Chars> findAllByIdIn(List<Long> ids);
}