package com.myungsang.myungsang_backend.video.iservice;

import com.myungsang.myungsang_backend.video.vo.VideoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VideoIService {
    VideoVo getVideo(long id);
}
