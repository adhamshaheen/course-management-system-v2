package com.example.admin.serviceImplementation;

import com.example.admin.dto.EnrollmentDTO;
import com.example.admin.entity.Enrollment;
import com.example.admin.repository.EnrollmentRepository;
import com.example.admin.service.IAdminEnrollmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminEnrollmentServiceImpl implements IAdminEnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public AdminEnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // ==========================
    // Retrieve all enrollments.
    // ==========================
    @Override
    public List<EnrollmentDTO> getAllEnrollments() {

        List<Enrollment> enrollments = enrollmentRepository.findAll();

        List<EnrollmentDTO> enrollmentDTOs = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {

            EnrollmentDTO dto = mapToDTO(enrollment);

            enrollmentDTOs.add(dto);
        }

        return enrollmentDTOs;
    }

    // =====================================
    // Retrieve a specific enrollment by ID.
    // =====================================
    @Override
    public EnrollmentDTO getEnrollmentById(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Enrollment not found with id: " + id));

        return mapToDTO(enrollment);
    }

    // ======================
    // Delete an enrollment.
    // ======================
    @Override
    public void deleteEnrollment(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Enrollment not found with id: " + id));

        enrollmentRepository.delete(enrollment);
    }

    // ============================================
    // Convert Enrollment entity to EnrollmentDTO.
    // ============================================
    private EnrollmentDTO mapToDTO(Enrollment enrollment) {
        return new EnrollmentDTO(
                enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId(),
                enrollment.getEnrollmentDate()
        );
    }
}