package com.example.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {

    private Long id;
    private String title;
    private String description;
    private Long instructorId;


    public CourseDTO() {
    }


    public CourseDTO(Long id, String title, String description, Long instructorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
    }
}