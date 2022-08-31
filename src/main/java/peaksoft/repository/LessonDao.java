package peaksoft.repository;

import peaksoft.model.Lesson;

import java.util.List;

public interface LessonDao {
    List<Lesson>getAllLessons(Long id );
    void addLesson(Long courseId, Lesson lesson);
    Lesson getLessonById(Long id);
    void updateLesson(Long id,Lesson lesson);
    void deleteLesson(Long id);


}
