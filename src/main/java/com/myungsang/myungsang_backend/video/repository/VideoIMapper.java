package com.myungsang.myungsang_backend.video.repository;

import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoIMapper {
    VideoVO getVideo(long id);
    void increaseLikeCnt(VideoVO videoVO);
    void decreaseLikeCnt(VideoVO videoVO);
}
