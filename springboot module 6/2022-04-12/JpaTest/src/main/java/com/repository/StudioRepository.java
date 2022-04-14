package com.repository;

import com.entities.Category;
import com.entities.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Long> {
    List<Studio> findAllByIdIn(List<Long> ids);
}