package com.myungsang.myungsang_backend.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myungsang.myungsang_backend.file.vo.FileVO;
import lombok.Data;

@Data
public class UserDTO {
    @JsonProperty
    private long id;

    @JsonProperty
    private FileVO fileVO;

    @JsonProperty
    private String email;

    @JsonProperty
    private String name;

    @JsonProperty
    private String password;
}
