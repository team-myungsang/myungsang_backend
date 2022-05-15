package com.myungsang.myungsang_backend.category;

import com.myungsang.myungsang_backend.category.iservice.CategoryIService;
import com.myungsang.myungsang_backend.category.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    private CategoryIService categoryIService;

    @GetMapping("categories")
    public List<CategoryVO> getCategories() {
        return categoryIService.getCategories();
    }

    @PostMapping("categories")
    public ResponseEntity<Map<String, Object>> saveCategory(@RequestBody CategoryVO categoryVO) {
        categoryIService.saveCategory(categoryVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("id", categoryVO.getId());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
