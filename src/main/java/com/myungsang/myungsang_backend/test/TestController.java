package com.myungsang.myungsang_backend.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myungsang.myungsang_backend.test.iservice.TestIService;
import com.myungsang.myungsang_backend.test.vo.TestVO;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestIService testIService;

    @GetMapping("test")
    public List<TestVO> test() {
        List<TestVO> testDataList = testIService.getTestData();
        return testDataList;
    }
    @GetMapping("/")
    public String hello() {
        return System.getProperty("user.dir");
    }

}
