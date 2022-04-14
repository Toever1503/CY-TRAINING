package com.practice_2022_04_06.service;

import com.practice_2022_04_06.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department findById(Long id);

    Department save(Department obj);

    boolean deleteById(Long id);

    List<Department> findAll(int pos, int size, String by, String direction);

    Long countAll();
}
