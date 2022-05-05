package com.myungsang.myungsang_backend.file.service;

import com.myungsang.myungsang_backend.file.iservice.FileIService;
import com.myungsang.myungsang_backend.file.repository.FileIMapper;
import com.myungsang.myungsang_backend.file.vo.FileVO;
import org.springframework.stereotype.Service;

@Service
public class FileService implements FileIService {
    public final FileIMapper fileIMapper;

    public FileService(FileIMapper fileIMapper) {
        this.fileIMapper = fileIMapper;
    }

    @Override
    public long saveFile(FileVO fileVO) {
        return fileIMapper.saveFile(fileVO);
    }

    @Override
    public FileVO getFile(long fileId) {
        return fileIMapper.getFile(fileId);
    }
}
