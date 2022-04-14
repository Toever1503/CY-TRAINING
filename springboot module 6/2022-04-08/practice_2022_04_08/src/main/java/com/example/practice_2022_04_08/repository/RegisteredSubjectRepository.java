package com.example.practice_2022_04_08.repository;

import com.example.practice_2022_04_08.domain.RegisteredSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisteredSubjectRepository extends JpaRepository<RegisteredSubject, Long> {
    Optional<RegisteredSubject> findByAvailSubjectId(Long availSubjectId);

    Page<RegisteredSubject> findAllByStudentId(Long studentId, Pageable page);


}
