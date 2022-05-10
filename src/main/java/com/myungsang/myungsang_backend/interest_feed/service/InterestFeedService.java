package com.myungsang.myungsang_backend.interest_feed.service;

import com.myungsang.myungsang_backend.interest_feed.iservice.InterestFeedIService;
import com.myungsang.myungsang_backend.interest_feed.repository.InterestFeedIMapper;
import com.myungsang.myungsang_backend.interest_feed.vo.InterestFeedVO;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestFeedService implements InterestFeedIService {

    @Autowired
    private InterestFeedIMapper interestFeedIMapper;

    @Override
    public void insertInterestFeed(InterestFeedVO interestFeedVO) {
        interestFeedIMapper.insertInterestFeed(interestFeedVO);
    }

    @Override
    public void deleteInterestFeed(InterestFeedVO interestFeedVO) {
        interestFeedIMapper.deleteInterestFeed(interestFeedVO);
    }

    @Override
    public List<VideoVO> getInterestFeed(InterestFeedVO interestFeedVO) {
        return interestFeedIMapper.getInterestFeed(interestFeedVO);
    }
}
