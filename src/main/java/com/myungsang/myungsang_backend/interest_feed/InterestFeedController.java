package com.myungsang.myungsang_backend.interest_feed;

import com.myungsang.myungsang_backend.interest_feed.iservice.InterestFeedIService;
import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;
import com.myungsang.myungsang_backend.security.JwtService;
import com.myungsang.myungsang_backend.video.dto.VideoDTO;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://myungsang-frontend.vercel.app"}, allowCredentials = "true")
public class InterestFeedController {

    @Autowired
    private InterestFeedIService interestFeedIService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/getInterestFeed")
    public ResponseEntity<Map<String, Object>> getInterestFeed(@RequestHeader("accessToken") String accessToken) {
        int user_id = 0;
        if (accessToken != null) {
            String decodedToken = jwtService.decodeTokenByHeaderString(accessToken);
            if (Objects.equals(decodedToken, "invalid")) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("msg", "Token Invalid");
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            } else if (Objects.equals(decodedToken, "expire")) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("msg", "Token Expired");
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            }
            user_id = Integer.parseInt(decodedToken);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("videos", interestFeedIService.getInterestFeed(user_id));

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
