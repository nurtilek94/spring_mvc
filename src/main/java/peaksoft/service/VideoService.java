package peaksoft.service;

import peaksoft.model.Video;

import java.util.List;


public interface VideoService {

    List<Video> getAllVideo(long id);


    void addVideo(Long lessonId,Video video);


    Video getVideoById(Long id);


    void updateVideo(Long id,Video video);


    void deleteVideo(Long id);
}
