package com.example.demo.service;

import com.example.demo.dto.StudentDTO;

public interface IStudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(Long id);

}