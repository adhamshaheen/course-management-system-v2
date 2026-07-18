package com.example.demo.service;

import com.example.demo.dto.EnrollmentDTO;

import java.util.List;

public interface IEnrollmentService {

    EnrollmentDTO enrollStudent(EnrollmentDTO enrollmentDTO);

    List<EnrollmentDTO> getStudentEnrollments(Long studentId);

    EnrollmentDTO getEnrollmentById(Long id);

    void deleteEnrollment(Long id);

}