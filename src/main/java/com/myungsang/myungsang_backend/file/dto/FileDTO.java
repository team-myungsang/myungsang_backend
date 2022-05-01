package com.myungsang.myungsang_backend.file.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class FileDTO {
    private long id;
    private String name;
    private String path;
    private String extension;
}
