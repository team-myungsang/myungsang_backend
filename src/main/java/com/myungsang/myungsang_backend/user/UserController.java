package com.myungsang.myungsang_backend.user;

import com.myungsang.myungsang_backend.file.iservice.FileIService;
import com.myungsang.myungsang_backend.file.vo.FileVO;
import com.myungsang.myungsang_backend.file.vo.FileVOBuilder;
import com.myungsang.myungsang_backend.service.S3Uploader;
import com.myungsang.myungsang_backend.security.JwtService;
import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    @Autowired
    public UserController(S3Uploader s3Uploader, UserIService userIService, FileIService fileIService) {
        this.s3Uploader = s3Uploader;
        this.userIService = userIService;
        this.fileIService = fileIService;
    }

    private final S3Uploader s3Uploader;
    UserIService userIService;
    FileIService fileIService;


    @Autowired
    private JwtService jwtService;

    @Value("${security.expire.refreshtoken}")
    private Long REFRESH_TOKEN_EXP_TIME;     //           // 7일 기준

    @GetMapping("users")
    public List<UserVO> getUsers() {
        return userIService.getUsers();
    }

    @GetMapping("users/{id}")
    public UserVO getUser(@PathVariable("id") long id) {
        return userIService.getUser(id);
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserVO userVO) {
        String msg = userIService.register(userVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", msg);
        return resultMap;
    }

    @PatchMapping("users/{id}")
    @ResponseBody
    public UserVO updateUser(@PathVariable("id") long id, @RequestBody UserVO userVO) {

        userIService.updateUser(userVO, id);

        return null;
    }

    @PostMapping("users/{id}/profile_image")
    @ResponseBody
    public String uploadProfileImage(@PathVariable long id, @RequestParam("profile_image") MultipartFile userProfileImage) throws IOException {
        String filePath = "uploads/users/" + id + "/profile_images" ;
        FileVO file = s3Uploader.upload(userProfileImage, filePath);
        fileIService.saveFile(file);

        userIService.updateUserFile(file.getId(), id);

        return null;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserVO userVO, HttpServletResponse response) {
        UserVO resultUser = userIService.getUserByLogin(userVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(resultUser == null) {
            resultMap.put("msg", "Wrong email");
            return resultMap;
        } else if(!encoder.matches(userVO.getPassword(), resultUser.getPassword())) {
            resultMap.put("msg", "Wrong password");
            return resultMap;
        }

        Map<String, Object> tokens = jwtService.createToken(resultUser);
        Cookie cookie = new Cookie("refreshToken", tokens.get("refreshToken").toString());
        cookie.setMaxAge(Integer.parseInt(String.valueOf(REFRESH_TOKEN_EXP_TIME)) / 1000);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        resultMap.put("msg", "Login succeed");
        resultMap.put("userId", resultUser.getId());
        resultMap.put("accessToken", tokens.get("accessToken"));
        return resultMap;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestBody UserVO userVO, HttpServletResponse response) {

        userVO.setRefreshToken("");
        userIService.saveRefreshToken(userVO);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", "Logout Succeed");
        return resultMap;
    }

    @PostMapping("/detoken")
    public Map<String, Object> detoken(@RequestBody UserVO userVO, HttpServletResponse response) {
        String secretKey = "vnsdlavnlksavnioerjwiobnrisodfhguowehrgnjdsflnlnbvoansiovnosdnolsdfnbgosenbodfb";
        String result = jwtService.decodeToken(userVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }
    @PostMapping("/validRefreshToken")
    public Map<String, Object> validRefreshToken(@RequestBody UserVO userVO, HttpServletResponse response) {
        Map<String, Object> resultMap = jwtService.validRefreshToken(userVO);
        return resultMap;
    }
}
