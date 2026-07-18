package com.example.demo.controller;

import com.example.demo.dto.CourseDTO;
import com.example.demo.service.IPublicCourseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/courses")
public class PublicCourseController {

    private final IPublicCourseService courseService;

    // Constructor
    public PublicCourseController(IPublicCourseService courseService) {
        this.courseService = courseService;
    }

    // ==========================
    // Get all available courses
    // ==========================
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        List<CourseDTO> courses = courseService.getAvailableCourses(page, size, sortBy);

        return ResponseEntity.ok(courses);
    }

    // ==========================
    // Get course by ID
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {

        CourseDTO course = courseService.getCourseById(id);

        return ResponseEntity.ok(course);
    }

}