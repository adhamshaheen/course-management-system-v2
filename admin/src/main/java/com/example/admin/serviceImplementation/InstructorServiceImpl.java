package com.example.admin.serviceImplementation;

import com.example.admin.dto.InstructorDTO;
import com.example.admin.entity.Instructor;
import com.example.admin.repository.InstructorRepository;
import com.example.admin.service.IInstructorService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class InstructorServiceImpl implements IInstructorService {

    private final InstructorRepository instructorRepository;


    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }


    // ===============================
    // CREATE INSTRUCTOR
    // ===============================
    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {

        Instructor instructor = new Instructor();

        instructor.setName(instructorDTO.getName());
        instructor.setSpecialization(instructorDTO.getSpecialization());

        Instructor savedInstructor = instructorRepository.save(instructor);

        return mapToDTO(savedInstructor);
    }


    // ===============================
    // GET ALL INSTRUCTORS
    // ===============================
    @Override
    public List<InstructorDTO> getInstructors(int page, int size, String sortBy) {

        if(page < 0 || size <= 0)
        {
            throw new RuntimeException(
                    "Page number and size must be valid"
            );
        }

        List<Instructor> instructors = instructorRepository.findAll();


        // Sorting
        if(sortBy != null && sortBy.equalsIgnoreCase("name"))
        {
            instructors.sort(
                    (i1,i2) ->
                            i1.getName().compareToIgnoreCase(i2.getName())
            );
        }
        else if(sortBy != null && sortBy.equalsIgnoreCase("specialization"))
        {
            instructors.sort(
                    (i1,i2) ->
                            i1.getSpecialization().compareToIgnoreCase(i2.getSpecialization())
            );
        }
        else
        {
            instructors.sort(
                    (i1,i2) ->
                            i1.getId().compareTo(i2.getId())
            );
        }


        // Pagination
        int start = page * size;
        int end = Math.min(start + size, instructors.size());

        if(start >= instructors.size())
        {
            return new ArrayList<>();
        }


        List<InstructorDTO> result = new ArrayList<>();

        for(Instructor instructor :instructors.subList(start,end))
        {
            result.add(mapToDTO(instructor));
        }

        return result;
    }


    // ===============================
    // GET INSTRUCTOR BY ID
    // ===============================
    @Override
    public InstructorDTO getInstructorById(Long id) {

        Instructor instructor =
                instructorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));

        return mapToDTO(instructor);
    }


    // ===============================
    // UPDATE INSTRUCTOR
    // ===============================
    @Override
    public InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO) {

        Instructor existingInstructor =
                instructorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));


        existingInstructor.setName(instructorDTO.getName());
        existingInstructor.setSpecialization(instructorDTO.getSpecialization());


        Instructor updatedInstructor =instructorRepository.save(existingInstructor);

        return mapToDTO(updatedInstructor);
    }


    // ===============================
    // DELETE INSTRUCTOR
    // ===============================
    @Override
    public void deleteInstructor(Long id) {

        Instructor instructor =
                instructorRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException("Instructor not found with id: " + id)
                            );

        instructorRepository.delete(instructor);
    }



    // ===============================
    // ENTITY -> DTO
    // ===============================
    private InstructorDTO mapToDTO(Instructor instructor) {
        return new InstructorDTO(
                instructor.getId(),
                instructor.getName(),
                instructor.getSpecialization()
        );
    }

}