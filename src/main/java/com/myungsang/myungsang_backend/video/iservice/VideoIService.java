package com.myungsang.myungsang_backend.video.iservice;

import com.myungsang.myungsang_backend.video.vo.VideoVO;

public interface VideoIService {
    VideoVO getVideo(long id);
    void clickLikeButton(VideoVO videoVO);
    void unclickLikeButton(VideoVO videoVO);
}
