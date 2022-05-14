package com.myungsang.myungsang_backend.admin.repository;

import com.myungsang.myungsang_backend.admin.vo.AdminVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminIMapper {
    void AdminUpdateShowId(AdminVO adminVO);
}
