package com.example.admin.controller;

import com.example.admin.dto.CourseDTO;
import com.example.admin.service.IAdminCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/courses")
public class AdminCourseController {

    private final IAdminCourseService courseService;

    public AdminCourseController(IAdminCourseService courseService) {
        this.courseService = courseService;
    }

    // =====================================
    // CREATE COURSE
    // =====================================
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {

        CourseDTO createdCourse = courseService.createCourse(courseDTO);

        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    // =====================================
    // GET ALL COURSES
    // =====================================
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        List<CourseDTO> courses = courseService.getCourses(page, size, sortBy);

        return ResponseEntity.ok(courses);
    }

    // =====================================
    // GET COURSE BY ID
    // =====================================
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {

        CourseDTO course = courseService.getCourseById(id);

        return ResponseEntity.ok(course);
    }

    // =====================================
    // UPDATE COURSE
    // =====================================
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseDTO courseDTO) {

        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);

        return ResponseEntity.ok(updatedCourse);
    }

    // =====================================
    // DELETE COURSE (SOFT DELETE)
    // =====================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.ok("Course deleted successfully.");
    }

}