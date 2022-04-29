package com.myungsang.myungsang_backend.video.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VideoVo {
    @JsonProperty
    private long id;

    @JsonProperty
    private long user_id;

    @JsonProperty
    private String title;

    @JsonProperty
    private int like;

    @JsonProperty
    private int view;

    @JsonProperty
    private long videoSize;

    @JsonProperty
    private long path;

    @JsonProperty
    private String created_at;

    @JsonProperty
    private String updated_at;
}
