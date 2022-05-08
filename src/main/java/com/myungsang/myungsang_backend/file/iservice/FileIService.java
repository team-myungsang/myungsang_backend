package com.myungsang.myungsang_backend.file.iservice;

import com.myungsang.myungsang_backend.file.vo.FileVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

public interface FileIService {
    long saveFile(FileVO fileVO);

    FileVO getFile(long fileId);
}
