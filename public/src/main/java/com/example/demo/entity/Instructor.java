package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "instructors")
@Getter
@Setter
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String specialization;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    public Instructor() {}

    public Instructor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }
}