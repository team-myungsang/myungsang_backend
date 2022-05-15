package com.myungsang.myungsang_backend.interest_feed;

import com.myungsang.myungsang_backend.interest_feed.iservice.InterestFeedIService;
import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;
import com.myungsang.myungsang_backend.security.JwtService;
import com.myungsang.myungsang_backend.video.dto.VideoDTO;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://myungsang-frontend.vercel.app"}, allowCredentials = "true")
public class InterestFeedController {

    @Autowired
    private InterestFeedIService interestFeedIService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/getInterestFeed")
    public List<VideoDTO> getInterestFeed(@RequestHeader("accessToken") String accessToken) {
        int user_id = 0;
        if (accessToken != null) {
            String decodedToken = jwtService.decodeTokenByHeaderString(accessToken);
            user_id = Integer.parseInt(decodedToken);
        }

        return interestFeedIService.getInterestFeed(user_id);
    }
}
