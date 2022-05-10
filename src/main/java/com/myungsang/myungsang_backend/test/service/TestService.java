package com.myungsang.myungsang_backend.test.service;

import com.myungsang.myungsang_backend.test.iservice.TestIService;
import com.myungsang.myungsang_backend.test.repository.TestIMapper;
import com.myungsang.myungsang_backend.test.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestService implements TestIService {
    @Autowired
    private TestIMapper testIMapper;

    @Override
    public List<TestVO> getTestData(){
        return testIMapper.getTestData();
    }
}
