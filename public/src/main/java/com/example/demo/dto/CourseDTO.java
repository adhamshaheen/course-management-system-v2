package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {

    private Long id;
    private String title;
    private String description;
    private String instructorName;


    public CourseDTO() {
    }


    public CourseDTO(Long id, String title, String description, String instructorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructorName = instructorName;
    }
}