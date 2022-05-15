package com.myungsang.myungsang_backend.video.iservice;

import com.myungsang.myungsang_backend.video.vo.VideoVO;

public interface VideoIService {
    VideoVO getVideo(long id);
    void saveVideo(VideoVO videoVO);
    void saveVideoFile(long id, long videoFileId);
    void saveThumbnailFile(long id, long thumbnailFileId);
    void clickLikeButton(VideoVO videoVO);
    void unclickLikeButton(VideoVO videoVO);
}
