package com.myungsang.myungsang_backend.main_feed.service;

import com.myungsang.myungsang_backend.main_feed.dto.MainFeedDTO;
import com.myungsang.myungsang_backend.main_feed.iservice.MainFeedIService;
import com.myungsang.myungsang_backend.main_feed.repository.MainFeedIMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainFeedService implements MainFeedIService {
    public final MainFeedIMapper mainFeedIMapper;

    public MainFeedService(MainFeedIMapper mainFeedIMapper) {
        this.mainFeedIMapper = mainFeedIMapper;
    }

    @Override
    public List<MainFeedDTO> getFeed(String view, int category_id, int page_index, int page_count) {
        return mainFeedIMapper.getFeed(view, category_id, page_index, page_count);
    }
}
