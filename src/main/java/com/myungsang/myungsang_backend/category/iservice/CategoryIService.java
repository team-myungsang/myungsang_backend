package com.myungsang.myungsang_backend.category.iservice;

import com.myungsang.myungsang_backend.category.vo.CategoryVO;

import java.util.List;

public interface CategoryIService {
    List<CategoryVO> getCategories();

    void saveCategory(CategoryVO categoryVO);
}
