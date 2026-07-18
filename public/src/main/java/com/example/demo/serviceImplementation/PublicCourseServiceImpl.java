package com.example.demo.serviceImplementation;

import com.example.demo.dto.CourseDTO;
import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.IPublicCourseService;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;



@Service
public class PublicCourseServiceImpl implements IPublicCourseService {

    private final CourseRepository courseRepository;

    public PublicCourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    // =====================================
    // GET AVAILABLE COURSES
    // =====================================
    @Override
    public List<CourseDTO> getAvailableCourses(int page, int size, String sortBy) {

        if(page < 0 || size <= 0)
        {
            throw new RuntimeException("Page number and size must be valid");
        }


        // Only return non-deleted courses
        List<Course> courses = courseRepository.findByDeletedFalse();


        // Sorting
        if(sortBy != null && sortBy.equalsIgnoreCase("title"))
        {
            courses.sort(
                    (c1,c2) ->
                            c1.getTitle().compareToIgnoreCase(c2.getTitle())
            );

        }
        else
        {
            courses.sort(
                    (c1,c2) ->
                            c1.getId().compareTo(c2.getId())
            );

        }


        // Pagination
        int start = page * size;
        int end = Math.min(start + size, courses.size());

        if(start >= courses.size())
        {
            return new ArrayList<>();
        }

        List<CourseDTO> result = new ArrayList<>();

        for(Course course : courses.subList(start,end))
        {
            result.add(mapToDTO(course));
        }

        return result;
    }


    // =====================================
    // GET COURSE DETAILS
    // =====================================
    @Override
    public CourseDTO getCourseById(Long id) {

        Course course =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                        new RuntimeException("Course not found with id: " + id)
                        );

        return mapToDTO(course);

    }


    // =====================================
    // ENTITY -> DTO
    // =====================================
    private CourseDTO mapToDTO(Course course)
    {
        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getInstructor().getName()
        );
    }

}