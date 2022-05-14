package com.myungsang.myungsang_backend.main_feed.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import lombok.Builder;
import lombok.Data;

public class MainFeedVO {

    private long id;
    private UserVO user;
    private String title;
    private int likeCnt;
    private int view;
    private long path;
    private String createdAt;
    private String updatedAt;
    private long showId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public long getPath() {
        return path;
    }

    public void setPath(long path) {
        this.path = path;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getShowId() {
        return showId;
    }

    public void setShowId(long showId) {
        this.showId = showId;
    }
}
