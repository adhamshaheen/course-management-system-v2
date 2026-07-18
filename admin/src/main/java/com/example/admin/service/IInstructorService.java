package com.example.admin.service;

import com.example.admin.dto.InstructorDTO;

import java.util.List;

public interface IInstructorService {

    InstructorDTO createInstructor(InstructorDTO instructorDTO);

    List<InstructorDTO> getInstructors(int page, int size, String sortBy);

    InstructorDTO getInstructorById(Long id);

    InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO);

    void deleteInstructor(Long id);

}