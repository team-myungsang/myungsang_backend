package com.myungsang.myungsang_backend.main_feed;


import com.myungsang.myungsang_backend.file.vo.FileVO;
import com.myungsang.myungsang_backend.main_feed.dto.MainFeedDTO;
import com.myungsang.myungsang_backend.main_feed.iservice.MainFeedIService;
import com.myungsang.myungsang_backend.main_feed.vo.MainFeedVO;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://myungsang-frontend.vercel.app"}, allowCredentials = "true")
public class MainFeedController {

    @Autowired
    public MainFeedController(MainFeedIService mainFeedIService) {
        this.mainFeedIService = mainFeedIService;
    }

    MainFeedIService mainFeedIService;

    @GetMapping("/main/videos")
    public List<MainFeedDTO> getFeed(
            @RequestParam(value = "view", required = false) String view,
            @RequestParam(value = "category_id", defaultValue = "0") int category_id,
            @RequestParam(value = "page_index", defaultValue = "-1") int page_index,
            @RequestParam(value = "page_count", defaultValue = "-1") int page_count
    ) {
        System.out.println("view = " + view);
        System.out.println("category_id = " + category_id);
        System.out.println("page_index = " + page_index);
        System.out.println("page_count = " + page_count);
        return mainFeedIService.getFeed(view, category_id, page_index, page_count);
    }
}
