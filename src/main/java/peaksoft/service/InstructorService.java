package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Instructor;

import java.util.List;

public interface InstructorService {

    List<Instructor> getAllInstructors(Long id);

    void addInstructor(Instructor instructor,Long companyId);


    Instructor getInstructorById(Long id);


    void updateInstructor(Long id,Instructor instructor);


    void deleteInstructor(Long id);


    void assignInstructorToCourse(Long courseId, Long instructorId);
}
