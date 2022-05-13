package com.myungsang.myungsang_backend.main_feed.service;

import com.myungsang.myungsang_backend.main_feed.iservice.MainFeedIService;
import com.myungsang.myungsang_backend.main_feed.repository.MainFeedIMapper;
import com.myungsang.myungsang_backend.main_feed.vo.MainFeedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainFeedService implements MainFeedIService {

    @Autowired
    private MainFeedIMapper mainFeedIMapper;

    @Override
    public List<MainFeedVO> mainVideos() {
        return mainFeedIMapper.mainVideos();
    }
}
