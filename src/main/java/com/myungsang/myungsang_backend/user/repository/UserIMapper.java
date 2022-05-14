package com.myungsang.myungsang_backend.user.repository;

import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserIMapper {

    List<UserVO> getUsers();
    UserVO getUser(long id);
    List<UserVO> checkEmail(String email);
    List<UserVO> checkName(String name);
    void updateName(UserVO userVO);
    List<UserVO> getEmailAndNameList();
    void register(UserVO userVO);
    void updateUser(@Param("item") UserVO userVO, @Param("id") long id);
    void updateUserFile(@Param("file_id") long fileId, @Param("id") long id);
    void saveRefreshToken(UserVO userVO);
    UserVO getUserByLogin(UserVO userVO);
    UserVO getUserByRefreshToken(UserVO userVO);
}
