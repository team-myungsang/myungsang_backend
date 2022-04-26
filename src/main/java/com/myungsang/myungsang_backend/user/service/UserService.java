package com.myungsang.myungsang_backend.user.service;

import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserService(UserIService mapper) {
        this.mapper = mapper;
    }

    public UserIService mapper;

    public List<UserVO> getUsers(){
        return mapper.getUsers();
    }
}
