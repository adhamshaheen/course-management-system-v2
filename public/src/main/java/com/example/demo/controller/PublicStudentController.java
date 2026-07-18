package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.IPublicStudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/students")
public class PublicStudentController {

    private final IPublicStudentService studentService;

    // Constructor
    public PublicStudentController(IPublicStudentService studentService) {
        this.studentService = studentService;
    }

    // ==========================
    // Register a new student
    // ==========================
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {

        StudentDTO createdStudent = studentService.createStudent(studentDTO);

        return ResponseEntity.ok(createdStudent);
    }

    // ==========================
    // Get student by ID
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {

        StudentDTO student = studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    // ==========================
    // Update student
    // ==========================
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDTO studentDTO) {

        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);

        return ResponseEntity.ok(updatedStudent);
    }

}