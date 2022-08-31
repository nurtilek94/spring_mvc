package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Course;
import peaksoft.repository.CourseDao;
import peaksoft.service.CourseService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getAllCourse(long id) {
        return courseDao.getAllCourse(id);
    }

    @Override
    public void addCourse(Course course, Long companyId) {
        courseDao.addCourse(course, companyId);

    }

    @Override
    public Course getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public void updateCourse(long id, Course course) {
        courseDao.updateCourse(id, course);

    }

    @Override
    public void deleteCourse(long id) {

        courseDao.deleteCourse(id);
    }

}
