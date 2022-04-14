package com.repository;

import com.entities.Category;
import com.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findAllByIdIn(List<Long> ids);
}