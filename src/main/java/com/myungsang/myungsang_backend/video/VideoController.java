package com.myungsang.myungsang_backend.video;

import com.myungsang.myungsang_backend.file.iservice.FileIService;
import com.myungsang.myungsang_backend.file.vo.FileVO;
import com.myungsang.myungsang_backend.interest_feed.iservice.InterestFeedIService;
import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;
import com.myungsang.myungsang_backend.service.S3Uploader;
import com.myungsang.myungsang_backend.video.iservice.VideoIService;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://myungsang-frontend.vercel.app"}, allowCredentials = "true")
public class VideoController {

    @Autowired
    private VideoIService videoIService;

    @Autowired
    private FileIService fileIService;

    @Autowired
    private InterestFeedIService interestFeedIService;

    private final S3Uploader s3Uploader;

    @Autowired
    public VideoController(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    @GetMapping("videos/{id}")
    public VideoVO getVideo(@PathVariable long id) {
        return videoIService.getVideo(id);
    }

    @PostMapping("/videos/{id}/upload_file")
    public Map<String, Object> uploadFile(@PathVariable long id, @RequestParam(value = "video_file", required = false) MultipartFile videoFile, @RequestParam(value = "thumbnail_file", required = false) MultipartFile thumbnailFile) throws IOException {
        String filePath = "uploads/videos/" + id;
        FileVO videoFileResult = null;
        FileVO thumbnailFileResult = null;

        if (videoFile != null) {
            videoFileResult = s3Uploader.upload(videoFile, filePath);
            fileIService.saveFile(videoFileResult);
            videoIService.saveVideoFile(id, videoFileResult.getId());
        }

        if (thumbnailFile != null) {
            thumbnailFileResult = s3Uploader.upload(thumbnailFile, filePath);
            fileIService.saveFile(thumbnailFileResult);
            videoIService.saveThumbnailFile(id, thumbnailFileResult.getId());
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("video_file", videoFileResult);
        resultMap.put("thumbnail_file", thumbnailFileResult);

        return resultMap;
    }

    @PostMapping("/increaseLikeCnt")
    public Map<String, Object> increaseLikeCnt(@RequestBody VideoVO videoVO, HttpServletResponse response) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", "LikeCount is increased");
        InterestFeedVO interestFeedVO = new InterestFeedVO();
        interestFeedVO.setUserId(videoVO.getUserId());
        interestFeedVO.setFileId(videoVO.getId());
        interestFeedIService.insertInterestFeed(interestFeedVO);
        videoIService.clickLikeButton(videoVO);
        return resultMap;
    }

    @PostMapping("/decreaseLikeCnt")
    public Map<String, Object> decreaseLikeCnt(@RequestBody VideoVO videoVO, HttpServletResponse response) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", "LikeCount is decreased");
        InterestFeedVO interestFeedVO = new InterestFeedVO();
        interestFeedVO.setUserId(videoVO.getUserId());
        interestFeedVO.setFileId(videoVO.getId());
        interestFeedIService.deleteInterestFeed(interestFeedVO);
        videoIService.unclickLikeButton(videoVO);
        return resultMap;
    }
}
