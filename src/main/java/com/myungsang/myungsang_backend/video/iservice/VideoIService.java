package com.myungsang.myungsang_backend.video.iservice;

import com.myungsang.myungsang_backend.category.vo.CategoryVO;
import com.myungsang.myungsang_backend.video.dto.VideoDTO;
import com.myungsang.myungsang_backend.video.vo.VideoVO;

import java.util.List;

public interface VideoIService {
    VideoDTO getVideo(long id, long user_id);

    List<VideoDTO> getMyVideos(long user_id);

    void saveVideo(VideoVO videoVO);

    void saveCategories(long videoId, List<CategoryVO> categoryVOList);

    void saveVideoFile(long id, long videoFileId);

    void saveThumbnailFile(long id, long thumbnailFileId);

    void clickLikeButton(VideoVO videoVO);

    void unclickLikeButton(VideoVO videoVO);

    void deleteVideo(long id);
}
