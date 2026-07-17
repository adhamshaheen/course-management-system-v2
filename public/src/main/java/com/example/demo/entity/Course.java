package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    private LocalDateTime registrationStartTime;

    private LocalDateTime registrationEndTime;

    private boolean deleted = false;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    // Default constructor
    public Course() {
    }

    // Parameterized constructor
    public Course(String title,
                  String description,
                  Instructor instructor,
                  LocalDateTime registrationStartTime,
                  LocalDateTime registrationEndTime) {

        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.registrationStartTime = registrationStartTime;
        this.registrationEndTime = registrationEndTime;
    }
}