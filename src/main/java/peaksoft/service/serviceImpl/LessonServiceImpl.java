package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonDao;
import peaksoft.service.LessonService;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class LessonServiceImpl implements LessonService {
    private final LessonDao lessonDao;
    @Autowired
    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public List<Lesson> getAllLessons(Long id) {
        return lessonDao.getAllLessons(id);
    }

    @Override
    public void addLesson(Long courseId,Lesson lesson) {
        lessonDao.addLesson(courseId, lesson);

    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonDao.getLessonById(id);
    }

    @Override
    public void updateLesson(Long id,Lesson lesson) {
        lessonDao.updateLesson(id,lesson);

    }

    @Override
    public void deleteLesson(Long id) {
        lessonDao.deleteLesson(id);

    }
}
