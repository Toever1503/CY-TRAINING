package com.repository;

import com.domain.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("select v from Video v where v.title like %?1% or v.description like %?1%")
    Page<Video> search(String q, Pageable pageable);
}