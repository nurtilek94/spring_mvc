package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;

import java.util.List;

public interface CourseDao {
    List<Course>getAllCourse(long id);
    void addCourse(Course course, Long companyId);
    Course getCourseById(Long id);
    void updateCourse(long id,Course course);
    void deleteCourse(Long id);
}
