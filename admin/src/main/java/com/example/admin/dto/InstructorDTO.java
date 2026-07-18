package com.example.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorDTO {

    private Long id;
    private String name;
    private String specialization;


    public InstructorDTO() {
    }

    
    public InstructorDTO(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}