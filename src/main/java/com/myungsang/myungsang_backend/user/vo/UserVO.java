package com.myungsang.myungsang_backend.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserVO {
    @JsonProperty
    private long id;

    @JsonProperty
    private String email;

    @JsonProperty
    private String name;

    @JsonProperty
    private String password;
}
