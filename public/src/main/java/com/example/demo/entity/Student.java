package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
}