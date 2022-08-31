package peaksoft.repository;

import peaksoft.model.Video;

import java.util.List;

public interface VideoDao {
    List<Video> getAllVideo(Long id);
    void addVideo(Long lessonId,Video video);
    Video getVideoById(Long id);
    void updateVideo(Long id, Video video);
    void deleteVideo(Long id);
}
