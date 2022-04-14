package com.repository;

import com.entities.Category;
import com.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByIdIn(List<Long> ids);
}