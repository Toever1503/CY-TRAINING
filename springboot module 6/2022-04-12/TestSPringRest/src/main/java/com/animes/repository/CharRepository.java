package com.animes.repository;

import com.animes.entities.Char;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharRepository extends JpaRepository<Char, Long> {
}