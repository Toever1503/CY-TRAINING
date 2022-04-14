package com.service;


import com.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    StudentDTO findById(Long id);

    List<StudentDTO> findAll();

    StudentDTO save(StudentDTO studentDTO);

    boolean delete(Long id);

}
