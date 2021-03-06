package com.myungsang.myungsang_backend.user;

import com.myungsang.myungsang_backend.file.iservice.FileIService;
import com.myungsang.myungsang_backend.file.vo.FileVO;
import com.myungsang.myungsang_backend.service.S3Uploader;
import com.myungsang.myungsang_backend.security.JwtService;
import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://myungsang-frontend.vercel.app"}, allowCredentials = "true")
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

    @Value("${security.expire.accesstoken}")
    private Long ACCESS_TOKEN_EXP_TIME;     //            // 20분 기준

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

    @GetMapping("users/me")
    public UserVO getMe(@RequestHeader("accessToken") String accessToken) {
        long userId = 0;
        String decodedToken = jwtService.decodeTokenByHeaderString(accessToken);
        userId = Integer.parseInt(decodedToken);

        return userIService.getUser(userId);
    }

    @PostMapping("checkEmail")
    public Map<String, Object> checkEmail(@RequestBody UserVO userVO) {
        String msg = userIService.checkEmail(userVO.getEmail());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", msg);
        return resultMap;
    }

    @PostMapping("checkName")
    public Map<String, Object> checkName(@RequestBody UserVO userVO) {
        String msg = userIService.checkName(userVO.getName());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", msg);
        return resultMap;
    }

    @PostMapping("updateName")
    public Map<String, Object> updateName(@RequestBody UserVO userVO) {
        String msg = userIService.updateName(userVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", msg);
        return resultMap;
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

    @PostMapping("users/profile_image")
    @ResponseBody
    public FileVO uploadProfileImage(@RequestHeader("accessToken") String accessToken, @RequestParam("profile_image") MultipartFile userProfileImage) throws IOException {
        long userId = 0;
        String decodedToken = jwtService.decodeTokenByHeaderString(accessToken);
        userId = Integer.parseInt(decodedToken);

        String filePath = "uploads/users/" + userId + "/profile_images";
        FileVO file = s3Uploader.upload(userProfileImage, filePath);
        fileIService.saveFile(file);

        userIService.updateUserFile(file.getId(), userId);

        return file;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserVO userVO, HttpServletResponse response) {
        UserVO resultUser = userIService.getUserByLogin(userVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (resultUser == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Wrong email");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        } else if (!encoder.matches(userVO.getPassword(), resultUser.getPassword())) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Wrong password");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> tokens = jwtService.createToken(resultUser);
        Cookie cookie = new Cookie("refreshToken", tokens.get("refreshToken").toString());
        cookie.setMaxAge(Integer.parseInt(String.valueOf(REFRESH_TOKEN_EXP_TIME)) / 1000);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader(HttpHeaders.SET_COOKIE, "SameSite=None");
        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
        boolean firstHeader = true;
        for (String header : headers) { // there can be multiple Set-Cookie attributes
            if (firstHeader) {
                response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=None"));
                firstHeader = false;
                continue;
            }
            response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=None"));
        }

        resultMap.put("msg", "Login succeed");
        resultMap.put("userId", resultUser.getId());
        resultMap.put("accessToken", tokens.get("accessToken"));
        resultMap.put("expTime", ACCESS_TOKEN_EXP_TIME);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
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

    @DeleteMapping("/users")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestHeader("accessToken") String accessToken) {
        long userId = 0;
        String decodedToken = jwtService.decodeTokenByHeaderString(accessToken);
        userId = Integer.parseInt(decodedToken);

        userIService.deleteUser(userId);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", "success");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
