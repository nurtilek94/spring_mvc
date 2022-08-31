package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.TaskDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class TaskRepositoryImpl implements TaskDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Task> getAllTasks(Long id) {
        return entityManager
                .createQuery("select t from Task t where t.lesson.id=:id",Task.class)
                .setParameter("id",id)
                .getResultList();
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task task1=entityManager.find(Task.class,id);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadline(task.getDeadline());
        entityManager.merge(task);

    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class,id);
    }

    @Override
    public void addTask(Long lessonId, Task task) {
        Lesson lesson=entityManager.find(Lesson.class,lessonId);
        lesson.addTasks(task);
        task.setLesson(lesson);
        entityManager.persist(task);

    }

    @Override
    public void delete(Long id) {
        Task task=entityManager.find(Task.class,id);
        task.setLesson(null);
        entityManager.remove(task);

    }
}
