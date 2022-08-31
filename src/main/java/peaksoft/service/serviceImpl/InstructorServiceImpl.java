package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Instructor;
import peaksoft.repository.InstructorDao;
import peaksoft.service.InstructorService;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {
    private final InstructorDao instructorDao;
    @Autowired
    public InstructorServiceImpl(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    @Override
    public List<Instructor> getAllInstructors(Long id) {
        return instructorDao.getAllInstructors(id);
    }

    @Override
    public void addInstructor(Instructor instructor, Long companyId) {
        instructorDao.addInstructor(instructor,companyId);

    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorDao.getInstructorById(id);
    }

    @Override
    public void updateInstructor(Long id,Instructor instructor) {
        instructorDao.updateInstructor(id,instructor);

    }

    @Override
    public void deleteInstructor(Long id) {
        instructorDao.deleteInstructor(id);

    }

    @Override
    public void assignInstructorToCourse(Long courseId, Long instructorId) {
        instructorDao.assignInstructorToCourse(courseId,instructorId);

    }
}
