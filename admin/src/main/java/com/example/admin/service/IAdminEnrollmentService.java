package com.example.admin.service;

import com.example.admin.dto.EnrollmentDTO;

import java.util.List;

public interface IAdminEnrollmentService {

    List<EnrollmentDTO> getAllEnrollments();

    EnrollmentDTO getEnrollmentById(Long id);

    void deleteEnrollment(Long id);

}