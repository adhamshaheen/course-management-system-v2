package com.example.demo.dto;


import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EnrollmentDTO {

    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDate enrollmentDate;


    public EnrollmentDTO() {
    }


    public EnrollmentDTO(Long id, Long studentId, Long courseId, LocalDate enrollmentDate){
        this.id=id;
        this.studentId=studentId;
        this.courseId=courseId;
        this.enrollmentDate=enrollmentDate;
    }
}