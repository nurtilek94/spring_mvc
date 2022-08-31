package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Student;
import peaksoft.repository.StudentDao;
import peaksoft.service.StudentService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class StudentRepositoryImpl implements StudentDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Student> getAllStudents(Long id) {
        return entityManager
                .createQuery("select s from Student s where s.company.id=:id",Student.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public void addStudent(Long id,Student student) {
        Company company = entityManager.find(Company.class,id);
        company.addStudent(student);
        student.setCompany(company);
        entityManager.merge(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class,id);
    }



    @Override
    public void updateStudent(Student student, Long id) {
        Student student1=entityManager.find(Student.class,id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);

    }
    @Override
    public void deleteStudent(Long id) {
        Student student=entityManager.find(Student.class,id);
        student.setCourse(null);
        entityManager.remove(student);
    }
}
