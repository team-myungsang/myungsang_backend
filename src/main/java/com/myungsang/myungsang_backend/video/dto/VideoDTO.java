package com.myungsang.myungsang_backend.video.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myungsang.myungsang_backend.file.vo.FileVO;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import lombok.Data;

@Data
public class VideoDTO {
    @JsonProperty
    private long id;

    @JsonProperty
    private UserVO user;

    @JsonProperty
    private String title;

    @JsonProperty
    private String content;

    @JsonProperty
    private FileVO thumbnail_file;

    @JsonProperty
    private FileVO video_file;

    @JsonProperty
    private int likeCnt;

    @JsonProperty
    private int view;

    @JsonProperty
    private long path;

    @JsonProperty
    private boolean liked;

    @JsonProperty
    private String created_at;

    @JsonProperty
    private String updated_at;
}
