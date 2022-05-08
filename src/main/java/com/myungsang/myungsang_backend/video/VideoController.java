package com.myungsang.myungsang_backend.video;

import com.myungsang.myungsang_backend.file.vo.FileVO;
import com.myungsang.myungsang_backend.interest_feed.iservice.InterestFeedIService;
import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;
import com.myungsang.myungsang_backend.video.iservice.VideoIService;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class VideoController {

    @Autowired
    private VideoIService videoIService;

    @Autowired
    private InterestFeedIService interestFeedIService;

    @GetMapping("videos/{id}")
    public VideoVO getVideo(@PathVariable long id) {
        return videoIService.getVideo(id);
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
