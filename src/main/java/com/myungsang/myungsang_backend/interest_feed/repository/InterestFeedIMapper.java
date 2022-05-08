package com.myungsang.myungsang_backend.interest_feed.repository;

import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterestFeedIMapper {

    void insertInterestFeed(InterestFeedVO interestFeedVO);
    void deleteInterestFeed(InterestFeedVO interestFeedVO);
}
