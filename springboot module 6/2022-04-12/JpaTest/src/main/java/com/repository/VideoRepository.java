package com.repository;

import com.entities.Category;
import com.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByIdIn(List<Long> ids);
}