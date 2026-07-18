package com.example.demo.controller;

import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.service.IPublicEnrollmentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/enrollments")
public class PublicEnrollmentController {

    private final IPublicEnrollmentService enrollmentService;

    // Constructor
    public PublicEnrollmentController(IPublicEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // ==========================
    // Enroll a student in a course
    // ==========================
    @PostMapping
    public ResponseEntity<EnrollmentDTO> enrollStudent(@RequestBody EnrollmentDTO enrollmentDTO) {

        EnrollmentDTO createdEnrollment = enrollmentService.enrollStudent(enrollmentDTO);

        return new ResponseEntity<>(createdEnrollment, HttpStatus.CREATED);
    }

    // ==========================
    // Get all enrollments for a student
    // ==========================
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getStudentEnrollments(@PathVariable Long studentId) {

        List<EnrollmentDTO> enrollments = enrollmentService.getStudentEnrollments(studentId);

        return ResponseEntity.ok(enrollments);
    }

    // ==========================
    // Get enrollment by ID
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id) {

        EnrollmentDTO enrollment = enrollmentService.getEnrollmentById(id);

        return ResponseEntity.ok(enrollment);
    }

}