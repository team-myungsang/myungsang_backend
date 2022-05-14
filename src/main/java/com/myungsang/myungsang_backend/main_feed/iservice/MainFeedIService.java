package com.myungsang.myungsang_backend.main_feed.iservice;

import com.myungsang.myungsang_backend.main_feed.dto.MainFeedDTO;
import com.myungsang.myungsang_backend.main_feed.vo.MainFeedVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MainFeedIService {
    List<MainFeedDTO> getFeed(String view, int category_id, int page_index, int page_count);
}
