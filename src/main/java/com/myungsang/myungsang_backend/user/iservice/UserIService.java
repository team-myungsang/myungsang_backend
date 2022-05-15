package com.myungsang.myungsang_backend.user.iservice;

import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserIService {
    List<UserVO> getUsers();

    UserVO getUser(long id);

    String checkEmail(String email);

    String checkName(String name);

    String updateName(UserVO userVO);

    String register(UserVO userVO);

    void updateUser(@Param("item") UserVO userVO, @Param("id") long id);

    void updateUserFile(@Param("file_id") long fileId, @Param("id") long id);

    void saveRefreshToken(UserVO userVO);

    UserVO getUserByLogin(UserVO userVO);

    UserVO getUserByRefreshToken(UserVO userVO);

    void deleteUser(long id);
}
