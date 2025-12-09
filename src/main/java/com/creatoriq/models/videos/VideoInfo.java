package com.creatoriq.models.videos;


import java.util.List;


public class VideoInfo {

    private String videoId;

    // Snippet
    private String channelId;
    private String title;
    private String description;
    private List<String> tags;
    private String thumbnailUrl;

    // ContentDetails
    private String duration;
    private boolean licensedContent;

    // Statistics
    private String viewCount;
    private String likeCount;
    private String commentCount;


    public VideoInfo(String videoId, String channelId, String title, String description, List<String> tags, String duration, boolean licensedContent, String viewCount, String likeCount, String commentCount, String thumbnailUrl) {
        this.videoId = videoId;
        this.channelId = channelId;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.duration = duration;
        this.licensedContent = licensedContent;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public VideoInfo() {
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isLicensedContent() {
        return licensedContent;
    }

    public void setLicensedContent(boolean licensedContent) {
        this.licensedContent = licensedContent;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
}





