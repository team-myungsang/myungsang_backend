package com.myungsang.myungsang_backend.user.service;

import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.user.repository.UserIMapper;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserIService {

    @Autowired
    public UserIMapper userIMapper;


    @Override
    public List<UserVO> getUsers() {
        return userIMapper.getUsers();
    }

    @Override
    public UserVO getUser(long id) {
        return userIMapper.getUser(id);
    }

    @Override
    public void register(UserVO userVO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String securePw = encoder.encode(userVO.getPassword());
        userVO.setPassword(securePw);
        userIMapper.register(userVO);
    }

    @Override
    public void updateUser(UserVO userVO, long id) {

    }

    @Override
    public void saveRefreshToken(UserVO userVO) {
        userIMapper.saveRefreshToken(userVO);
    }

    @Override
    public UserVO getUserByLogin(UserVO userVO) {
        return userIMapper.getUserByLogin(userVO);
    }
}
