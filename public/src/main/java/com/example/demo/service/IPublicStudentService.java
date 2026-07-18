package com.example.demo.service;

import com.example.demo.dto.StudentDTO;

public interface IPublicStudentService {

    StudentDTO createStudent(StudentDTO dto);

    StudentDTO getStudentById(Long id);

    StudentDTO updateStudent(Long id, StudentDTO dto);

}