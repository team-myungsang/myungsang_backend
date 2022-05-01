package com.myungsang.myungsang_backend.file.service;

import com.myungsang.myungsang_backend.file.iservice.FileIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired
    public FileService(FileIService mapper) {
        this.mapper = mapper;
    }

    public FileIService mapper;

}
