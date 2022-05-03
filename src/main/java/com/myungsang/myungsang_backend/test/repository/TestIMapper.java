package com.myungsang.myungsang_backend.test.repository;

import com.myungsang.myungsang_backend.test.vo.TestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestIMapper {
    List<TestVO> getTestData();
}
