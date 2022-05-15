package com.myungsang.myungsang_backend.video.repository;

import com.myungsang.myungsang_backend.video.dto.VideoDTO;
import com.myungsang.myungsang_backend.video.vo.VideoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoIMapper {
    VideoDTO getVideo(@Param("id") long id, @Param("user_id") long user_id);

    List<VideoDTO> getMyVideos(@Param("user_id") long user_id);

    void saveVideo(VideoVO videoVO);

    Integer getMaxShowId();

    void saveCategories(@Param("video_id") long videoId, @Param("category_id") long categoryId);

    void saveVideoFile(@Param("id") long id, @Param("video_file_id") long videoFileId);

    void saveThumbnailFile(@Param("id") long id, @Param("thumbnail_file_id") long thumbnailFileId);

    void increaseLikeCnt(VideoVO videoVO);

    void decreaseLikeCnt(VideoVO videoVO);

    void deleteVideo(long id);
}
