package com.myungsang.myungsang_backend.interest_feed.iservice;

import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;
import com.myungsang.myungsang_backend.video.vo.VideoVO;

import java.util.List;

public interface InterestFeedIService {

    void insertInterestFeed(InterestFeedVO interestFeedVO);
    void deleteInterestFeed(InterestFeedVO interestFeedVO);
    List<VideoVO> getInterestFeed(InterestFeedVO interestFeedVO);
}
