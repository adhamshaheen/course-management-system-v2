package com.example.admin.repository;

import com.example.admin.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // List to filter out the soft deleted courses (automatically)
    // SELECT * FROM courses WHERE deleted = false;
    List<Course> findByDeletedFalse();

}