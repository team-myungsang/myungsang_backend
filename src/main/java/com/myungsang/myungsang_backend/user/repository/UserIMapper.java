package com.myungsang.myungsang_backend.user.repository;

import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserIMapper {

    List<UserVO> getUsers();
    UserVO getUser(long id);
    void register(UserVO userVO);
    void updateUser(@Param("item") UserVO userVO, @Param("id") long id);
    void updateUserFile(@Param("file_id") long fileId, @Param("id") long id);
    void saveRefreshToken(UserVO userVO);
    UserVO getUserByLogin(UserVO userVO);
}
