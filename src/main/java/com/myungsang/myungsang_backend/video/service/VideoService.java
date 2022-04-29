package com.myungsang.myungsang_backend.video.service;

import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.video.iservice.VideoIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    @Autowired
    public VideoService(VideoIService mapper) {
        this.mapper = mapper;
    }

    public VideoIService mapper;
}
