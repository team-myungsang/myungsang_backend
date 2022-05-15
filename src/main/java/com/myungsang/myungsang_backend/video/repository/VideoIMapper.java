package com.myungsang.myungsang_backend.video.repository;

import com.myungsang.myungsang_backend.video.dto.VideoDTO;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VideoIMapper {
    VideoDTO getVideo(long id);
    void saveVideo(VideoVO videoVO);
    void saveVideoFile(@Param("id") long id, @Param("video_file_id") long videoFileId);
    void saveThumbnailFile(@Param("id") long id, @Param("thumbnail_file_id") long thumbnailFileId);
    void increaseLikeCnt(VideoVO videoVO);
    void decreaseLikeCnt(VideoVO videoVO);


}
