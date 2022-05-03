package com.myungsang.myungsang_backend.user.iservice;

import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserIService {
    List<UserVO> getUsers();
    UserVO getUser(long id);
    void register(UserVO userVO);
    void updateUser(@Param("item") UserVO userVO, @Param("id") long id);
    void saveRefreshToken(UserVO userVO);
    UserVO getUserByLogin(UserVO userVO);
}
