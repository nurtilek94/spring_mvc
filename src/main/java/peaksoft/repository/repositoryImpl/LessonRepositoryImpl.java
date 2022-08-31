package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class LessonRepositoryImpl implements LessonDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Lesson> getAllLessons(Long id) {
        return entityManager
                .createQuery("select l from Lesson l where l.course.id = : id",Lesson.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public void addLesson(Long courseId, Lesson lesson) {
        Course course = entityManager.find(Course.class, courseId);
        course.addLesson(lesson);
        lesson.setCourse(course);
        entityManager.persist(lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return entityManager.find(Lesson.class,id);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        Lesson lesson1=entityManager.find(Lesson.class,id);
        lesson1.setLessonName(lesson.getLessonName());
        lesson1.setVideo(lesson.getVideo());
        entityManager.merge(lesson1);

    }
    @Override
    public void deleteLesson(Long id) {
        Lesson lesson=entityManager.find(Lesson.class,id);
        lesson.setCourse(null);
        entityManager.remove(lesson);

    }
}
