package com.myungsang.myungsang_backend.admin;

import com.myungsang.myungsang_backend.admin.iservice.AdminIService;
import com.myungsang.myungsang_backend.admin.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AdminController {

    @Autowired
    private AdminIService adminIService;

//    @PostMapping("adminTest")
//    public Map<String, Object> adminTest(@RequestBody AdminVO adminVO) {
//        String msg = adminIService.adminTest(adminVO);
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap.put("msg", msg);
//        return resultMap;
//    }

    @PostMapping("adminUpdateShowId")
    public Map<String, Object> adminUpdateShowId(@RequestBody AdminVO adminVO) {
        String msg = adminIService.adminUpdateShowId(adminVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", msg);
        return resultMap;
    }
}
