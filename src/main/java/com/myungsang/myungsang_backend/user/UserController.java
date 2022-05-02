package com.myungsang.myungsang_backend.user;

import com.myungsang.myungsang_backend.file.iservice.FileIService;
import com.myungsang.myungsang_backend.file.vo.FileVO;
import com.myungsang.myungsang_backend.user.dto.UserDTO;
import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserController(UserIService userIService, FileIService fileIService) {
        this.userIService = userIService;
        this.fileIService = fileIService;
    }

    UserIService userIService;
    FileIService fileIService;

    @GetMapping("users")
    public List<UserVO> getUsers() {
        return userIService.getUsers();
    }

    @GetMapping("users/{id}")
    public UserVO getUser(@PathVariable("id") long id) {
        return userIService.getUser(id);
    }

    @PostMapping("register")
    @ResponseBody
    public UserVO createUser(@RequestBody UserDTO userDTO) {
        userIService.saveUser(userDTO);

        return null;
    }

    @PatchMapping("users/{id}")
    @ResponseBody
    public UserVO updateUser(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {

        userIService.updateUser(userDTO, id);

        return null;
    }

    @PostMapping("users/{id}/profile_image")
    @ResponseBody
    public String uploadProfileImage(@PathVariable long id, @RequestParam("profile_image") MultipartFile userProfileImage) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filePath = "/uploads/" + id + "/profile_images/";
        String fileName = "profile_" + timestamp;
        String fileExtension = StringUtils.getFilenameExtension(userProfileImage.getOriginalFilename());

        FileVO fileVO = null;
        fileVO.setName(filePath);
        fileVO.setName(fileName);
        fileVO.setName(fileExtension);

        fileIService.saveFile(fileVO);

        return null;
    }
}
