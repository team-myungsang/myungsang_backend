package com.myungsang.myungsang_backend.file.iservice;

import com.myungsang.myungsang_backend.file.vo.FileVO;

public interface FileIService {
    void saveFile(FileVO fileVO);

    FileVO getFile(long fileId);
}
