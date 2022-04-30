package com.myungsang.myungsang_backend.main_feed;


import com.myungsang.myungsang_backend.main_feed.iservice.MainFeedIService;
import com.myungsang.myungsang_backend.main_feed.vo.MainFeedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainFeedController {

    @Autowired
    public MainFeedController(MainFeedIService mainFeedIService) {
        this.mainFeedIService = mainFeedIService;
    }

    MainFeedIService mainFeedIService;

    @GetMapping("feed")
    public List<MainFeedVO> getFeed() {
        return mainFeedIService.getFeed();
    }
}
