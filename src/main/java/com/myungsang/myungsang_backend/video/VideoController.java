package com.myungsang.myungsang_backend.video;

import com.myungsang.myungsang_backend.file.iservice.FileIService;
import com.myungsang.myungsang_backend.file.vo.FileVO;
import com.myungsang.myungsang_backend.interest_feed.iservice.InterestFeedIService;
import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;
import com.myungsang.myungsang_backend.security.JwtService;
import com.myungsang.myungsang_backend.service.S3Uploader;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import com.myungsang.myungsang_backend.video.dto.VideoDTO;
import com.myungsang.myungsang_backend.video.iservice.VideoIService;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://myungsang-frontend.vercel.app"}, allowCredentials = "true")
public class VideoController {

    @Autowired
    private VideoIService videoIService;

    @Autowired
    private FileIService fileIService;

    @Autowired
    private InterestFeedIService interestFeedIService;

    @Autowired
    private JwtService jwtService;

    private final S3Uploader s3Uploader;

    @Autowired
    public VideoController(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    @GetMapping("videos/{id}")
    public VideoDTO getVideo(@PathVariable long id) {
        return videoIService.getVideo(id);
    }

    @PostMapping("/videos")
    public ResponseEntity<Map<String, Object>> saveVideo(@RequestHeader("Authorization") String authorization, @RequestBody VideoVO videoVO) {
        String user = jwtService.decodeTokenByHeaderString(authorization);
        if (Objects.equals(user, "invalid")) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "invalid token");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        } else if (Objects.equals(user, "expire")) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "token expire");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        videoVO.setUserId(Integer.parseInt(user));
        videoIService.saveVideo(videoVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("id", videoVO.getId());


        return new ResponseEntity<>(resultMap, HttpStatus.OK);
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
