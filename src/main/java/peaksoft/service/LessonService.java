package peaksoft.service;

import peaksoft.model.Lesson;

import java.util.List;

public interface LessonService  {

    List<Lesson> getAllLessons(Long id);


    void addLesson(Long id,Lesson lesson);


    Lesson getLessonById(Long id);


    void updateLesson(Long id,Lesson lesson);


    void deleteLesson(Long id);
}
