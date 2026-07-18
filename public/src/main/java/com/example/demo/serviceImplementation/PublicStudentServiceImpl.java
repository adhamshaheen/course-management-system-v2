package com.example.demo.serviceImplementation;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.IPublicStudentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PublicStudentServiceImpl implements IPublicStudentService {

    private final StudentRepository studentRepository;

    public PublicStudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ==================================================
    // CREATE STUDENT
    // ==================================================

    @Override
    public StudentDTO createStudent(StudentDTO dto) {

        studentRepository.findByEmail(dto.getEmail())
                .ifPresent(student -> {
                    throw new RuntimeException(
                            "Student with email "
                                    + dto.getEmail()
                                    + " already exists"
                    );
                });

        Student student = new Student();

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        Student savedStudent = studentRepository.save(student);

        return mapToDTO(savedStudent);
    }

    // ==================================================
    // GET STUDENT BY ID
    // ==================================================

    @Override
    public StudentDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        return mapToDTO(student);
    }

    // ==================================================
    // UPDATE STUDENT
    // ==================================================

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        studentRepository.findByEmail(dto.getEmail())
                .ifPresent(existingStudent -> {

                    if (!existingStudent.getId().equals(id)) {

                        throw new RuntimeException(
                                "Student with email "
                                        + dto.getEmail()
                                        + " already exists"
                        );
                    }
                });

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        Student updatedStudent = studentRepository.save(student);

        return mapToDTO(updatedStudent);
    }

    // ==================================================
    // ENTITY -> DTO
    // ==================================================

    private StudentDTO mapToDTO(Student student) {

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail()
        );
    }

}