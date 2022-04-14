package com.example.practice_2022_04_08.repository;

import com.example.practice_2022_04_08.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
