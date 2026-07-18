package com.example.admin.serviceImplementation;

import com.example.admin.dto.StudentDTO;
import com.example.admin.entity.Student;
import com.example.admin.repository.StudentRepository;
import com.example.admin.service.IAdminStudentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminStudentServiceImpl implements IAdminStudentService {

    private final StudentRepository studentRepository;

    public AdminStudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ==================================================
    // GET ALL STUDENTS
    // ==================================================

    @Override
    public List<StudentDTO> getStudents(int page, int size, String sortBy) {

        if (page < 0 || size <= 0) {
            throw new RuntimeException("Page number and page size must be greater than zero");
        }

        List<Student> students = studentRepository.findAll();

        if ("name".equalsIgnoreCase(sortBy)) {

            students.sort(
                    (s1, s2) ->
                            s1.getName().compareToIgnoreCase(s2.getName())
            );

        } else if ("email".equalsIgnoreCase(sortBy)) {

            students.sort(
                    (s1, s2) ->
                            s1.getEmail().compareToIgnoreCase(s2.getEmail())
            );

        } else {

            students.sort(
                    (s1, s2) ->
                            s1.getId().compareTo(s2.getId())
            );
        }

        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, students.size());

        if (startIndex >= students.size()) {
            return new ArrayList<>();
        }

        List<StudentDTO> result = new ArrayList<>();

        for (Student student : students.subList(startIndex, endIndex)) {
            result.add(mapToDTO(student));
        }

        return result;
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
    // DELETE STUDENT
    // ==================================================

    @Override
    public void deleteStudent(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }

        studentRepository.deleteById(id);
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