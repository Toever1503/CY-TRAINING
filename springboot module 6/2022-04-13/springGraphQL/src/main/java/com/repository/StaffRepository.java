package com.repository;

import com.domain.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query(value = "select * from staff as ss join videoandstaff as vas on vas.staff = ss.id where vas.video = :vId", nativeQuery = true)
    Page<Staff> findAllStaffsByVideoId(@Param("vId") Long videoId, Pageable page);
}