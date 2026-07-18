package com.example.admin.serviceImplementation;

import com.example.admin.dto.CourseDTO;
import com.example.admin.entity.Course;
import com.example.admin.entity.Instructor;
import com.example.admin.repository.CourseRepository;
import com.example.admin.repository.InstructorRepository;
import com.example.admin.service.IAdminCourseService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminCourseServiceImpl implements IAdminCourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    // Constructor
    public AdminCourseServiceImpl(
            CourseRepository courseRepository,
            InstructorRepository instructorRepository
    ) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }


    // ===============================
    // CREATE COURSE
    // ===============================
    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {

        // Search for the instructor -using their ID- in the repository and throw exception if not found
        Instructor instructor =
                instructorRepository.findById(courseDTO.getInstructorId())
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Instructor not found with id: "
                                                + courseDTO.getInstructorId()
                                )
                        );

        // Create a course object
        Course course = new Course();

        // Set the course details to the currently created course object
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setInstructor(instructor);
        course.setDeleted(false);

        Course savedCourse = courseRepository.save(course);

        // Return created course dto
        return mapToDTO(savedCourse);
    }


    // ===============================
    // GET COURSES WITH PAGINATION
    // ===============================
    @Override
    public List<CourseDTO> getCourses(int page, int size, String sortBy) {

        // Check page size if valid, otherwise, throw an exception
        if(page < 0 || size <= 0)
        {
            throw new RuntimeException(
                    "Page number and size must be valid"
            );
        }

        // Create a list of active courses
        List<Course> courses = courseRepository.findByDeletedFalse();


        // Sorting
        if(sortBy != null && sortBy.equalsIgnoreCase("title"))
        {

            courses.sort(
                    (c1,c2) ->
                            c1.getTitle()
                                    .compareToIgnoreCase(
                                            c2.getTitle()
                                    )
            );

        }
        else
        {

            courses.sort(
                    (c1,c2) ->
                            c1.getId()
                                    .compareTo(
                                            c2.getId()
                                    )
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


    // ===============================
    // GET COURSE BY ID
    // ===============================
    @Override
    public CourseDTO getCourseById(Long id) {

        Course course =
                courseRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Course not found with id: "
                                                + id
                                )
                        );

        return mapToDTO(course);
    }


    // ===============================
    // UPDATE COURSE
    // ===============================
    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {

        // Search for the course in the repository and if not found, throw an exception
        Course existingCourse =
                courseRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Course not found with id: "
                                                + id
                                )
                        );


        Instructor instructor = instructorRepository.findById(courseDTO.getInstructorId())
                                                        .orElseThrow(
                                                                () -> new RuntimeException(
                                                                        "Instructor not found with id: "
                                                                                + courseDTO.getInstructorId()
                                                                )
                                                        );

        existingCourse.setTitle(courseDTO.getTitle());
        existingCourse.setDescription(courseDTO.getDescription());
        existingCourse.setInstructor(instructor);

        Course updatedCourse = courseRepository.save(existingCourse);

        return mapToDTO(updatedCourse);
    }


    // ===============================
    // SOFT DELETE COURSE
    // ===============================
    @Override
    public void deleteCourse(Long id) {

        Course course =
                courseRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        course.setDeleted(true);

        courseRepository.save(course);
    }


    // ===============================
    // ENTITY --> DTO
    // ===============================
    private CourseDTO mapToDTO(Course course) {

        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getInstructor().getId()
        );
    }

}