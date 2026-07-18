package com.example.admin.repository;

import com.example.admin.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent_Id(Long studentId);

    List<Enrollment> findByCourse_Id(Long courseId);

    boolean existsByStudent_IdAndCourse_Id(Long studentId, Long courseId);

}