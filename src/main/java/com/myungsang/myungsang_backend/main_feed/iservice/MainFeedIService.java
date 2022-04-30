package com.myungsang.myungsang_backend.main_feed.iservice;

import com.myungsang.myungsang_backend.main_feed.vo.MainFeedVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface MainFeedIService {
    List<MainFeedVO> getFeed();
}
