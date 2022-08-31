package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.InstructorDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Instructor> getAllInstructors(Long id) {
        return entityManager
                .createQuery("select i from Instructor i",Instructor.class)
                .getResultList();
    }
    @Override
    public void addInstructor(Instructor instructor,Long companyId) {
            Company company=entityManager.find(Company.class,companyId);
            company.addInstructor(instructor);
            instructor.setCompany(company);
            entityManager.persist(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
     public void updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 =entityManager.find(Instructor.class,id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        entityManager.merge(instructor1);

    }

    @Override
    public void deleteInstructor(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        for (Course c :instructor.getCourses()) {
            c.setInstructors(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public void assignInstructorToCourse(Long courseId, Long instructorId) {
       Instructor instructor= entityManager.find(Instructor.class,instructorId);
       Course course= entityManager.find(Course.class,courseId);
       instructor.addCourse(course);
       course.addInstructor(instructor);
       entityManager.merge(instructor);
       entityManager.merge(course);

    }
}
