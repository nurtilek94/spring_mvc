package peaksoft.service;


import org.springframework.stereotype.Service;
import peaksoft.model.Course;

import java.util.List;

public interface CourseService{

    List<Course> getAllCourse(long id);


    void addCourse(Course course, Long companyId);


    Course getCourseById(Long id);


    void updateCourse(long id,Course course);


    void deleteCourse(long id );
}
