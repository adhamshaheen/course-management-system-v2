package com.example.admin.controller;

import com.example.admin.dto.StudentDTO;
import com.example.admin.service.IAdminStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/students")
public class AdminStudentController {

    private final IAdminStudentService studentService;

    // Constructor
    public AdminStudentController(IAdminStudentService studentService) {
        this.studentService = studentService;
    }

    // ==========================
    // Get all students
    // ==========================
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        List<StudentDTO> students = studentService.getStudents(page, size, sortBy);

        return ResponseEntity.ok(students);
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
    // Delete student
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student deleted successfully.");
    }

}