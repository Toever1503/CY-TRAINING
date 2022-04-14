package com.controller;

import com.dto.StudentDTO;
import com.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(this.studentService.findAll());
    }

    @GetMapping(value = "{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(this.studentService.findById(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addStudent(@RequestBody StudentDTO stu) {
        return ResponseEntity.ok(this.studentService.save(stu));
    }

    @PatchMapping(value = "{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentDTO stu) {
        stu.setId(id);
        return ResponseEntity.ok(this.studentService.save(stu));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(this.studentService.delete(id));
    }
}
