package com.myungsang.myungsang_backend.user;

import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserController(UserIService userIService) {
        this.userIService = userIService;
    }

    UserIService userIService;

    @GetMapping("users")
    public List<UserVO> test() {
        return userIService.getUsers();
    }
}
