package com.example.admin.service;

import com.example.admin.dto.CourseDTO;

import java.util.List;

public interface IAdminCourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    List<CourseDTO> getCourses(int page, int size, String sortBy);

    CourseDTO getCourseById(Long id);

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    void deleteCourse(Long id);

}