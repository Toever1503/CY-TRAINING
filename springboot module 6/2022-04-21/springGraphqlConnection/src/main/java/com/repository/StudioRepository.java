package com.repository;

import com.domain.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudioRepository extends JpaRepository<Studio, Long> {

    @Query("select s from Studio s join Videoandstudio vs on s.id = vs.id.studio where vs.id.video = ?1")
    Page<Studio> findAllStudiosByVideoId(Long id, Pageable pageable);

    @Query("select s from Studio s join Videoandstudio vs on s.id = vs.id.studio where s.name like %?1% and vs.id.video = ?2")
    Page<Studio> searchByAndVideoId(String search, Long id, Pageable pageable);

    Page<Studio> findAllByNameLike(String search, Pageable pageable);
}