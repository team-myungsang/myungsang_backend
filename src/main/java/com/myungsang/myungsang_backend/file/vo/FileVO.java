package com.myungsang.myungsang_backend.file.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

public class FileVO {

    private long id;
    private String name;
    private String path;
    private String extension;
    private String fullPath;

    public FileVO(long id, String name, String path, String extension, String fullPath) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.extension = extension;
        this.fullPath = fullPath;
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

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}
