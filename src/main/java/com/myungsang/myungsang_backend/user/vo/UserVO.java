package com.myungsang.myungsang_backend.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myungsang.myungsang_backend.file.vo.FileVO;
import lombok.Builder;
import lombok.Data;

public class UserVO {
    @JsonProperty
    private long id;

    @JsonProperty
    private String email;

    @JsonProperty
    private String name;

    @JsonIgnore
    private String password;

    @JsonProperty
    private FileVO file;

    public UserVO(long id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
