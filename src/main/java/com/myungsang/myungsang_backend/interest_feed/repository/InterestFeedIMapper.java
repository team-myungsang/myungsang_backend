package com.myungsang.myungsang_backend.interest_feed.repository;

import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterestFeedIMapper {

    void insertInterestFeed(InterestFeedVO interestFeedVO);
    void deleteInterestFeed(InterestFeedVO interestFeedVO);
    List<VideoVO> getInterestFeed(InterestFeedVO interestFeedVO);
}
