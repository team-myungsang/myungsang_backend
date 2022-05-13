package com.myungsang.myungsang_backend.main_feed.repository;

import com.myungsang.myungsang_backend.main_feed.vo.MainFeedVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainFeedIMapper {
    List<MainFeedVO> mainVideos();
}
