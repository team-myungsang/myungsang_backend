package com.myungsang.myungsang_backend.user;

import com.myungsang.myungsang_backend.file.iservice.FileIService;
import com.myungsang.myungsang_backend.file.vo.FileVO;
import com.myungsang.myungsang_backend.file.vo.FileVOBuilder;
import com.myungsang.myungsang_backend.service.S3Uploader;
import com.myungsang.myungsang_backend.security.JwtServiceCreate;
import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private JwtServiceCreate jwtServiceCreate;

    @GetMapping("users")
    public List<UserVO> getUsers() {
        return userIService.getUsers();
    }

    @GetMapping("users/{id}")
    public UserVO getUser(@PathVariable("id") long id) {
        return userIService.getUser(id);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserVO userVO) {
        userIService.register(userVO);

        return "joinSuccess";
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
//    public String login(@RequestBody UserVO userVO, HttpServletResponse response) {
        UserVO resultUser = userIService.getUserByLogin(userVO);
        Map<String, Object> tokens = jwtServiceCreate.createToken(resultUser);

        Cookie cookie = new Cookie("refreshToken", tokens.get("refreshToken").toString());
        cookie.setMaxAge(604800);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(userVO.getPassword(), resultUser.getPassword())) {
            System.out.println("password correct!!!");
        } else {
            System.out.println("password wrong...");
        }

//        String SECRET_KEY = "vnsdlavnlksavnioerjwiobnrisodfhguowehrgnjdsflnlnbvoansiovnosdnolsdfnbgosenbodfb";
//        System.out.println(tokens.get("accessToken").toString());
//        System.out.println(jwtServiceCreate.decodeToken(tokens.get("accessToken").toString(), SECRET_KEY));

        return tokens;
//        return tokens.get("accessToken").toString();
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap.put("accessToken", tokens.get("accessToken"));
//        return resultMap;
    }
}
