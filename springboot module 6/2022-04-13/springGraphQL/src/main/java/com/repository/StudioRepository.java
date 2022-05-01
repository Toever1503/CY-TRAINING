package com.repository;

import com.domain.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudioRepository extends JpaRepository<Studio, Long> {
    @Query(value = "select * from studio as ss join videoandstudio as vas on vas.studio = ss.id where vas.video = :vId", nativeQuery = true)
    Page<Studio> findAllStaffsByVideoId(@Param("vId") Long videoId, Pageable page);
}