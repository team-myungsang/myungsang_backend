package com.myungsang.myungsang_backend.category.service;

import com.myungsang.myungsang_backend.category.iservice.CategoryIService;
import com.myungsang.myungsang_backend.category.repository.CategoryIMapper;
import com.myungsang.myungsang_backend.category.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryIService {
    @Autowired
    private CategoryIMapper categoryIMapper;

    @Override
    public List<CategoryVO> getCategories(){
        return categoryIMapper.getCategories();
    }

    @Override
    public void saveCategory(CategoryVO categoryVO) {
        categoryIMapper.saveCategory(categoryVO);
    }


}
