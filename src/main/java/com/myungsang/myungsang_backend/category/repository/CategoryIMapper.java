package com.myungsang.myungsang_backend.category.repository;

import com.myungsang.myungsang_backend.category.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryIMapper {
    List<CategoryVO> getCategories();

    void saveCategory(CategoryVO categoryVO);
}
