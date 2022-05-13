package com.myungsang.myungsang_backend.video.vo;

public class VideoVO {
    private long id;
    private long userId;
    private long VideoFileId;
    private long thumbnailFileId;
    private String title;
    private int likeCnt;
    private int view;
    private long videoSize;
    private long path;
    private String createdAt;
    private String updatedAt;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getThumbnailFileId() {
        return thumbnailFileId;
    }

    public void setThumbnailFileId(long thumbnailFileId) {
        this.thumbnailFileId = thumbnailFileId;
    }

    public long getVideoFileId() {
        return VideoFileId;
    }

    public void setVideoFileId(long videoFileId) {
        VideoFileId = videoFileId;
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

    public long getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(long videoSize) {
        this.videoSize = videoSize;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
