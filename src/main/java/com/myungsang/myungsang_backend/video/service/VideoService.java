package com.myungsang.myungsang_backend.video.service;

import com.myungsang.myungsang_backend.category.vo.CategoryVO;
import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.video.dto.VideoDTO;
import com.myungsang.myungsang_backend.video.iservice.VideoIService;
import com.myungsang.myungsang_backend.video.repository.VideoIMapper;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService implements VideoIService {

    @Autowired
    private VideoIMapper videoIMapper;

    @Override
    public VideoDTO getVideo(long id, long user_id) {
        return videoIMapper.getVideo(id, user_id);
    }

    @Override
    public List<VideoDTO> getMyVideos(long user_id) {
        return videoIMapper.getMyVideos(user_id);
    }

    @Override
    public void saveVideo(VideoVO videoVO) {
        Integer maxShowId = videoIMapper.getMaxShowId();
        if(maxShowId == null) {
            videoVO.setShowId(1);
        } else {
            videoVO.setShowId(maxShowId + 1);
        }
        videoIMapper.saveVideo(videoVO);
    }

    @Override
    public void saveCategories(long videoId, List<CategoryVO> categoryVOList) {
        categoryVOList.forEach(categoryVO -> videoIMapper.saveCategories(videoId, categoryVO.getId()));
    }

    @Override
    public void saveVideoFile(long id, long videoFileId) {
        videoIMapper.saveVideoFile(id, videoFileId);
    }

    @Override
    public void saveThumbnailFile(long id, long thumbnailFileId) {
        videoIMapper.saveThumbnailFile(id, thumbnailFileId);
    }

    @Override
    public void clickLikeButton(VideoVO videoVO) {
        videoIMapper.increaseLikeCnt(videoVO);
    }

    @Override
    public void unclickLikeButton(VideoVO videoVO) {
        videoIMapper.decreaseLikeCnt(videoVO);
    }

    @Override
    public void deleteVideo(long id) {
        videoIMapper.deleteVideo(id);
    }
}
