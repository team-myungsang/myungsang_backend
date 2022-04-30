package com.myungsang.myungsang_backend.user.iservice;

import com.myungsang.myungsang_backend.user.dto.UserDTO;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserIService {
    List<UserVO> getUsers();

    UserVO getUser(long id);

    void saveUser(UserDTO userDTO);

    void updateUser(@Param("item") UserDTO userDTO, @Param("id") long id);
}
