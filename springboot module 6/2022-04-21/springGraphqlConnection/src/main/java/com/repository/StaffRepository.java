package com.repository;

import com.domain.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query(value = "select s from Staff s join Videoandstaff vs on s.id = vs.id.staff where vs.id.video = ?1")
    Page<Staff> findAllStaffsByVideoId(Long videoId, Pageable page);

    @Query(value = "select s from Staff s join Videoandstaff vs on s.id = vs.id.staff where s.name like %?1% and vs.id.video = ?1")
    Page<Staff> searchByAndVideoId(String name, Long videoId, Pageable page);

    Page<Staff> findAllByNameLike(String name, Pageable page);
}