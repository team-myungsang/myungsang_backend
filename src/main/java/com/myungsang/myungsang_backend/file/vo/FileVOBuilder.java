package com.myungsang.myungsang_backend.file.vo;

public class FileVOBuilder {
    private long id;
    private String name;
    private String path;
    private String extension;

    public FileVOBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public FileVOBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public FileVOBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public FileVOBuilder setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public FileVO createFileVO() {
        return new FileVO(id, name, path, extension);
    }
}