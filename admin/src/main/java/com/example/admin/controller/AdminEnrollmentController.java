package com.example.admin.controller;

import com.example.admin.dto.EnrollmentDTO;
import com.example.admin.service.IAdminEnrollmentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/enrollments")
public class AdminEnrollmentController {

    private final IAdminEnrollmentService enrollmentService;

    // Constructor
    public AdminEnrollmentController(IAdminEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // ==========================
    // Get all enrollments
    // ==========================
    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {

        List<EnrollmentDTO> enrollments = enrollmentService.getAllEnrollments();

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

    // ==========================
    // Delete enrollment
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return ResponseEntity.ok("Enrollment deleted successfully.");
    }

}