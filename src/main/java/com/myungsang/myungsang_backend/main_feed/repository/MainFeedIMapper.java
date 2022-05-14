package com.myungsang.myungsang_backend.main_feed.repository;

import com.myungsang.myungsang_backend.main_feed.dto.MainFeedDTO;
import com.myungsang.myungsang_backend.main_feed.vo.MainFeedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MainFeedIMapper {
    List<MainFeedDTO> getFeed(
            @Param("view") String view,
            @Param("category_id") int category_id,
            @Param("page_index") int page_index,
            @Param("page_count") int page_count
    );
}
