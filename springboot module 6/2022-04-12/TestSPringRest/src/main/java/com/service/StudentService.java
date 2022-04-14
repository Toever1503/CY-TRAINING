package com.service;

import com.dto.StudentDTO;
import com.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO findById(Long id) {
        return StudentDTO.toDTO(this.studentRepository.findById(id).orElse(null));
    }

    @Override
    public List<StudentDTO> findAll() {
        return this.studentRepository.findAll().stream().map(StudentDTO::toDTO).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        return StudentDTO.toDTO(this.studentRepository.save(StudentDTO.toObj(studentDTO)));
    }

    @Override
    public boolean delete(Long id) {
        this.studentRepository.deleteById(id);
        return true;
    }
}
