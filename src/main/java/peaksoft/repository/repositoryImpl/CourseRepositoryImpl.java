package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.CourseDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CourseRepositoryImpl implements CourseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Course> getAllCourse(long id) {
        return entityManager.
                createQuery("select c from Course c where c.company.id=:id",Course.class)
                .setParameter("id",id)
                .getResultList();
    }

    @Override
    public void addCourse(Course course, Long companyId) {
        Company company=entityManager.find(Company.class,companyId);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.merge(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    public void updateCourse(long id, Course course) {
        Course course1 = entityManager.find(Course.class,id);
        course1.setCourseName(course.getCourseName());
        course1.setDescription(course.getDescription());
        course1.setDuration(course.getDuration());
        course1.setDateOfStart(course.getDateOfStart());
        entityManager.merge(course1);

    }

    @Override
    public void deleteCourse(Long id) {
        Course course = entityManager.find(Course.class, id);
        for (Instructor c :course.getInstructors()) {
            c.setCourses(null);
        }
        course.setCompany(null);
        entityManager.remove(course);
    }
}
