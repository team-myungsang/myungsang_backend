package com.myungsang.myungsang_backend.video;

import com.myungsang.myungsang_backend.video.iservice.VideoIService;
import com.myungsang.myungsang_backend.video.vo.VideoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class VideoController {

    @Autowired
    public VideoController(VideoIService videoIService) {
        this.videoIService = videoIService;
    }

    VideoIService videoIService;

    @GetMapping("videos/{id}")
    public VideoVo getVideo(@PathVariable long id) {
        return videoIService.getVideo(id);
    }
}
