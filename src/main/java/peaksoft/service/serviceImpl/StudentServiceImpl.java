package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.model.Student;
import peaksoft.repository.StudentDao;
import peaksoft.service.StudentService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
   private StudentDao studentDao;


    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents(Long id) {
        return studentDao.getAllStudents(id);
    }


    @Override
    public Student getStudentById(Long id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student,Long id) {
        studentDao.updateStudent(student,id);
    }

    @Override
    public void deleteStudent(Long id) {
studentDao.deleteStudent(id);
    }

    @Override
    public void addStudent(Long comId, Student student) {
        studentDao.addStudent(comId,student);
    }
}
