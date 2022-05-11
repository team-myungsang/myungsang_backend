package com.myungsang.myungsang_backend.interest_feed;

import com.myungsang.myungsang_backend.interest_feed.iservice.InterestFeedIService;
import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InterestFeedController {

    @Autowired
    private InterestFeedIService interestFeedIService;

    @GetMapping("/getInterestFeed")
    public List<VideoVO> getInterestFeed(@RequestBody InterestFeedVO interestFeedVO) {
        return interestFeedIService.getInterestFeed(interestFeedVO);
    }
}
