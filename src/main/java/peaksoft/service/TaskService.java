package peaksoft.service;

import peaksoft.model.Task;

import java.util.List;


public interface TaskService  {

    List<Task> getAllTasks(Long id);


    void updateTask(Long id,Task task);


    Task getTaskById(Long id);


    void addTask(Long lessonId, Task task);


    void delete(Long id);
}
