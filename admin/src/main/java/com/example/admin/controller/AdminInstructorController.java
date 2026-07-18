package com.example.admin.controller;

import com.example.admin.dto.InstructorDTO;
import com.example.admin.service.IInstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/instructors")
public class AdminInstructorController {

    private final IInstructorService instructorService;

    // Constructor
    public AdminInstructorController(IInstructorService instructorService) {
        this.instructorService = instructorService;
    }

    // ==========================
    // Create a new instructor
    // ==========================
    @PostMapping
    public ResponseEntity<InstructorDTO> createInstructor(@RequestBody InstructorDTO instructorDTO) {

        InstructorDTO createdInstructor = instructorService.createInstructor(instructorDTO);

        return new ResponseEntity<>(createdInstructor, HttpStatus.CREATED);
    }

    // ==========================
    // Get all instructors
    // ==========================
    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getInstructors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        List<InstructorDTO> instructors = instructorService.getInstructors(page, size, sortBy);

        return ResponseEntity.ok(instructors);
    }

    // ==========================
    // Get instructor by ID
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable Long id) {

        InstructorDTO instructor = instructorService.getInstructorById(id);

        return ResponseEntity.ok(instructor);
    }

    // ==========================
    // Update instructor
    // ==========================
    @PutMapping("/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(
            @PathVariable Long id,
            @RequestBody InstructorDTO instructorDTO) {

        InstructorDTO updatedInstructor = instructorService.updateInstructor(id, instructorDTO);

        return ResponseEntity.ok(updatedInstructor);
    }

    // ==========================
    // Delete instructor
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable Long id) {

        instructorService.deleteInstructor(id);

        return ResponseEntity.ok("Instructor deleted successfully.");
    }

}