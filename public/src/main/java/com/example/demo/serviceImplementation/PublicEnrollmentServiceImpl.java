package com.example.demo.serviceImplementation;

import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.IPublicEnrollmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublicEnrollmentServiceImpl implements IPublicEnrollmentService {


    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    public PublicEnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    /**
     * Enroll a student in a course.
     *
     * Business rules:
     * 1. Student must exist.
     * 2. Course must exist.
     * 3. Course must not be deleted.
     * 4. Registration time must be valid.
     * 5. Student cannot enroll twice in the same course.
     */
    @Override
    public EnrollmentDTO enrollStudent(EnrollmentDTO enrollmentDTO) {


        // 1. Validate student existence
        Student student = studentRepository.findById(enrollmentDTO.getStudentId())
                                                .orElseThrow(() ->
                                                        new RuntimeException(
                                                                "Student not found with id: "
                                                                        + enrollmentDTO.getStudentId()
                                                        )
                                                );


        // 2. Validate course existence
        Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                                            .orElseThrow(() ->
                                                    new RuntimeException(
                                                            "Course not found with id: "
                                                                    + enrollmentDTO.getCourseId()
                                                    )
                                            );


        // 3. Check if course is soft deleted
        if (course.isDeleted()) {
            throw new RuntimeException("Cannot enroll in a deleted course");
        }

        // 4. Validate registration time window
        validateRegistrationWindow(course);


        // 5. Prevent duplicate enrollment
        boolean alreadyEnrolled =
                enrollmentRepository.existsByStudent_IdAndCourse_Id(student.getId(), course.getId());


        if (alreadyEnrolled) {
            throw new RuntimeException("Student is already enrolled in this course");
        }


        // 6. Create enrollment
        Enrollment enrollment = new Enrollment(student, course, LocalDate.now());


        // 7. Save enrollment
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);


        return mapToDTO(savedEnrollment);
    }



    /**
     * Retrieve all courses enrolled by a student.
     */
    @Override
    public List<EnrollmentDTO> getStudentEnrollments(Long studentId) {

        // Validate student exists
        studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with id: " + studentId
                        )
                );


        // Retrieve student's enrollments
        List<Enrollment> enrollments =
                enrollmentRepository.findByStudent_Id(studentId);


        // Convert entities to DTOs
        List<EnrollmentDTO> enrollmentDTOs = new ArrayList<>();


        for (Enrollment enrollment : enrollments) {

            EnrollmentDTO dto = mapToDTO(enrollment);

            enrollmentDTOs.add(dto);
        }


        return enrollmentDTOs;
    }



    /**
     * Retrieve an enrollment by its ID.
     */
    @Override
    public EnrollmentDTO getEnrollmentById(Long id) {

        Enrollment enrollment =
                enrollmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Enrollment not found with id: " + id)
                        );

        return mapToDTO(enrollment);
    }



    /**
     * Checks whether the current time is inside the registration window.
     */
    private void validateRegistrationWindow(Course course) {


        LocalDateTime now = LocalDateTime.now();


        if (now.isBefore(course.getRegistrationStartTime())) {

            throw new RuntimeException(
                    "Registration has not started yet. "
                            + "Registration starts at: "
                            + course.getRegistrationStartTime()
            );
        }


        if (now.isAfter(course.getRegistrationEndTime())) {

            throw new RuntimeException(
                    "Registration period has ended. "
                            + "Registration ended at: "
                            + course.getRegistrationEndTime()
            );
        }

    }



    /**
     * Convert Enrollment entity to EnrollmentDTO.
     */
    private EnrollmentDTO mapToDTO(Enrollment enrollment) {

        return new EnrollmentDTO(
                enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId(),
                enrollment.getEnrollmentDate()
        );
    }

}