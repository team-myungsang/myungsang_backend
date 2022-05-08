package com.myungsang.myungsang_backend.interest_feed.iservice;

import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;

public interface InterestFeedIService {

    void insertInterestFeed(InterestFeedVO interestFeedVO);
    void deleteInterestFeed(InterestFeedVO interestFeedVO);
}
