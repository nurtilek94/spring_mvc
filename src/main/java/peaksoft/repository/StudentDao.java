package peaksoft.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import peaksoft.model.Student;

import java.util.List;
@Service
public interface StudentDao {
    List<Student>getAllStudents(Long id);

    Student getStudentById(Long id);
    void updateStudent(Student student, Long id);
    void deleteStudent(Long id);
    void addStudent(Long comId, Student student);
}
