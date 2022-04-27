package com.myungsang.myungsang_backend.user;

import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserController(UserIService userIService) {
        this.userIService = userIService;
    }

    UserIService userIService;

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
    public UserVO createUser(@RequestBody UserVO userVO) {
        userIService.saveUser(userVO);

        return null;
    }

    @PatchMapping("update/{id}")
    @ResponseBody
    public UserVO updateUser(@PathVariable("id") long id, @RequestBody UserVO userVO) {

        System.out.println("userVO = " + userVO);
        userIService.updateUser(userVO, id);

        return null;
    }
}
