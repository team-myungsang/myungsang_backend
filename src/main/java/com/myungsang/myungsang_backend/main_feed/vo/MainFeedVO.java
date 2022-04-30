package com.myungsang.myungsang_backend.main_feed.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MainFeedVO {
    @JsonProperty
    private long id;

    @JsonProperty
    private UserVO user;

    @JsonProperty
    private String title;

    @JsonProperty
    private int like;

    @JsonProperty
    private int view;

    @JsonProperty
    private long path;

    @JsonProperty
    private String created_at;

    @JsonProperty
    private String updated_at;
}
