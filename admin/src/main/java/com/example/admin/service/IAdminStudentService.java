package com.example.admin.service;

import com.example.admin.dto.StudentDTO;

import java.util.List;

public interface IAdminStudentService {

    List<StudentDTO> getStudents(int page, int size, String sortBy);

    StudentDTO getStudentById(Long id);

    void deleteStudent(Long id);

}