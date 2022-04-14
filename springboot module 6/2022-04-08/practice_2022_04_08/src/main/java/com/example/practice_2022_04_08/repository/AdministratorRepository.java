package com.example.practice_2022_04_08.repository;

import com.example.practice_2022_04_08.domain.Adminstrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Adminstrator, Long> {
    Optional<Adminstrator> findByUsername(String username);
}
