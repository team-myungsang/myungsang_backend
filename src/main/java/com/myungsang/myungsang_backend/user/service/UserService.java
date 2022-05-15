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
    public String checkEmail(String email) {
        List<UserVO> resultList = userIMapper.checkEmail(email);
        if(resultList.isEmpty()) {
            return "Email check complete";
        } else {
            return "Same email exists";
        }
    }

    @Override
    public String checkName(String name) {
        List<UserVO> resultList = userIMapper.checkName(name);
        if(resultList.isEmpty()) {
            return "Name check complete";
        } else {
            return "Same name exists";
        }
    }

    @Override
    public String updateName(UserVO userVO) {
        List<UserVO> resultList = userIMapper.checkName(userVO.getName());
        if(resultList.isEmpty()) {
            userIMapper.updateName(userVO);
            return "Name update succeed";
        } else {
            return "Same name exists";
        }
    }

    @Override
    public String register(UserVO userVO) {
        List<UserVO> emailAndNameList = userIMapper.getEmailAndNameList();
        for(UserVO list: emailAndNameList) {
            if(userVO.getEmail().equals(list.getEmail())) {
                return "Same email exists";
            } else if(userVO.getName().equals(list.getName())) {
                return "Same name exists";
            }
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String securePw = encoder.encode(userVO.getPassword());
        userVO.setPassword(securePw);
        userVO.setType("Normal");
        userIMapper.register(userVO);
        return "Register succeed";
    }

    @Override
    public void updateUser(UserVO userVO, long id) {
        userIMapper.updateUser(userVO, id);
    }

    @Override
    public void updateUserFile(long fileId, long id) {
        userIMapper.updateUserFile(fileId, id);
    }

    @Override
    public void saveRefreshToken(UserVO userVO) {
        userIMapper.saveRefreshToken(userVO);
    }

    @Override
    public UserVO getUserByLogin(UserVO userVO) {
        return userIMapper.getUserByLogin(userVO);
    }

    @Override
    public UserVO getUserByRefreshToken(UserVO userVO) {
        return userIMapper.getUserByRefreshToken(userVO);
    }

    @Override
    public void deleteUser(long id) {
        userIMapper.deleteUser(id);
    }
}
