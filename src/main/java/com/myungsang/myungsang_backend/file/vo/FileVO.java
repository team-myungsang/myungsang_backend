package com.myungsang.myungsang_backend.file.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileVO {
    @JsonProperty
    private long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String path;
    @JsonProperty
    private String extension;
}
