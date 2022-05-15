package com.myungsang.myungsang_backend.main_feed;


import com.myungsang.myungsang_backend.file.vo.FileVO;
import com.myungsang.myungsang_backend.main_feed.dto.MainFeedDTO;
import com.myungsang.myungsang_backend.main_feed.iservice.MainFeedIService;
import com.myungsang.myungsang_backend.main_feed.vo.MainFeedVO;
import com.myungsang.myungsang_backend.security.JwtService;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://myungsang-frontend.vercel.app"}, allowCredentials = "true")
public class MainFeedController {

    @Autowired
    MainFeedIService mainFeedIService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/main/videos")
    public List<MainFeedDTO> getFeed(
            @RequestHeader(value = "accessToken") String accessToken,
            @RequestParam(value = "view", required = false) String view,
            @RequestParam(value = "category_id", defaultValue = "0") int category_id,
            @RequestParam(value = "page_index", defaultValue = "-1") int page_index,
            @RequestParam(value = "page_count", defaultValue = "-1") int page_count
    ) {
        System.out.println("accessToken = " + accessToken);
        String decodedToken = jwtService.decodeTokenByHeaderString(accessToken);
        int user_id = Integer.parseInt(decodedToken);

        return mainFeedIService.getFeed(view, category_id, page_index, page_count, user_id);
    }
}
