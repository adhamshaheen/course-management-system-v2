package com.example.demo.service;

import com.example.demo.dto.CourseDTO;
import java.util.List;

public interface IPublicCourseService {

    List<CourseDTO> getAvailableCourses(int page, int size, String sortBy);

    CourseDTO getCourseById(Long id);

}