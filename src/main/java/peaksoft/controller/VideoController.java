package peaksoft.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Video;
import peaksoft.service.VideoService;

@Controller
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }
    @GetMapping("/videos/{videoId}")
    private String getAllVideos(@PathVariable("videoId") Long videoId, Model model) {
        model.addAttribute("videoId",videoId);
        model.addAttribute("videos",videoService.getAllVideo(videoId));
        return "video/videos";
    }

    @GetMapping("/{id}/addVideo")
    private String addVideo(@PathVariable Long id, Model model){
        model.addAttribute("id",videoService.getVideoById(id));
        model.addAttribute("video",new Video());
        return "video/addVideo";
    }

    @PostMapping("/{videoId}/saveVideo")
    private String saveVideo(@PathVariable("videoId") Long id ,
                             @ModelAttribute("video")Video video) {
        videoService.addVideo(id,video);
        return "redirect:/videos/videos/"+ id;
    }

    @GetMapping("/update/{videoId}")
    private String updateVideo(@PathVariable("videoId") Long id , Model model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("video",video);
        model.addAttribute("lessonId",video.getLesson().getId());
        return "video/updateVideo";
    }
    @PostMapping("/{id}/{lessonId}/update")
    private String saveUpdateVideo(@PathVariable ("id") Long id ,
                                   @PathVariable ("lessonId") Long lessonId,
                                   @ModelAttribute("video") Video video) {
        videoService.updateVideo(id,video);
        return "redirect:/videos/videos/ "+ lessonId;
    }
    @PostMapping("/{lessonId}/{videoId}")
    private String deleteVideo(@PathVariable Long lessonId ,
                               @PathVariable Long videoId) {
        videoService.deleteVideo(videoId);
        return "redirect:/videos/videos/ " +lessonId;
    }
}
