package com.myungsang.myungsang_backend.test.iservice;

import com.myungsang.myungsang_backend.test.vo.TestVO;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

public interface TestIService {
    List<TestVO> getTestData();
}
