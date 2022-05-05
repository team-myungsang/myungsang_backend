package com.myungsang.myungsang_backend.file.repository;

import com.myungsang.myungsang_backend.file.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileIMapper {
    long saveFile(FileVO fileVO);

    FileVO getFile(long id);
}
