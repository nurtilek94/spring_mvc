package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Video;
import peaksoft.repository.VideoDao;
import peaksoft.service.VideoService;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class VideoServiceImpl implements VideoService {
    private final VideoDao videoDao;
    @Autowired
    public VideoServiceImpl(VideoDao videoDao) {
        this.videoDao = videoDao;
    }

    @Override
    public List<Video> getAllVideo(long id) {
        return null;
    }

    @Override
    public void addVideo(Long lessonId, Video video) {
        videoDao.addVideo(lessonId,video);

    }

    @Override
    public Video getVideoById(Long id) {
        return null;
    }

    @Override
    public void updateVideo(Long id, Video video) {

    }

    @Override
    public void deleteVideo(Long id) {

    }

}
