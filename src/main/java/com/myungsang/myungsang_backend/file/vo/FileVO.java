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

    @JsonProperty
    private String full_path;

    public String getFull_path() {
        return full_path;
    }

    public void setFull_path(String full_path) {
        this.full_path = full_path;
    }

    public FileVO(long id, String name, String path, String extension) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.extension = extension;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
