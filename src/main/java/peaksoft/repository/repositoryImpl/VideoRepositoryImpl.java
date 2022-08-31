package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;
import peaksoft.model.Video;
import peaksoft.repository.VideoDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class VideoRepositoryImpl implements VideoDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Video> getAllVideo(Long id) {
        return entityManager
                .createQuery("select v from Video v",Video.class)
                .getResultList();
    }

    @Override
    public void addVideo(Long lessonId, Video video) {
        Lesson lesson = entityManager.find(Lesson.class,lessonId);
        lesson.addVideo(video);
        video.setLesson(lesson);
        entityManager.merge(video);

    }

    @Override
    public Video getVideoById(Long id) {
        return entityManager.find(Video.class,id);
    }

    @Override
    public void updateVideo(Long id, Video video) {
        Video video1 = entityManager.find(Video.class,id);
        video1.setVideoName(video.getVideoName());
        video1.setLink(video.getLink());
        entityManager.merge(video);

    }

    @Override
    public void deleteVideo(Long id) {
        Video video = entityManager.find(Video.class,id);
        video.setLesson(null);
        entityManager.remove(video);

    }
}
