package com.example.admin.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate enrollmentDate;

    // Default constructor
    public Enrollment() {
    }

    // Parameterized constructor
    public Enrollment(Student student,
                      Course course,
                      LocalDate enrollmentDate) {

        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }
}