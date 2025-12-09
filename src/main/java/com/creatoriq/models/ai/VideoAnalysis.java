package com.creatoriq.models.ai;


import java.time.LocalDateTime;
import java.util.List;

public class VideoAnalysis {


    private String summary;
    private String contentQuality;
    private Double engagementScore;
    private List<String> strengths;
    private List<String> improvements;
    private List<String> suggestedTopics;

    private String seoAnalysis;
    private String thumbnailSuggestions;
    private String targetedAudience;


    private LocalDateTime analyzedDate;
    private String modelUsed;


    public VideoAnalysis() {
    }

    public VideoAnalysis(String summary, String contentQuality, Double engagementScore, List<String> strengths, List<String> improvements, List<String> suggestedTopics, String seoAnalysis, String thumbnailSuggestions, String targetedAudience, LocalDateTime analyzedDate, String modelUsed) {
        this.summary = summary;
        this.contentQuality = contentQuality;
        this.engagementScore = engagementScore;
        this.strengths = strengths;
        this.improvements = improvements;
        this.suggestedTopics = suggestedTopics;
        this.seoAnalysis = seoAnalysis;
        this.thumbnailSuggestions = thumbnailSuggestions;
        this.targetedAudience = targetedAudience;
        this.analyzedDate = analyzedDate;
        this.modelUsed = modelUsed;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContentQuality() {
        return contentQuality;
    }

    public void setContentQuality(String contentQuality) {
        this.contentQuality = contentQuality;
    }

    public Double getEngagementScore() {
        return engagementScore;
    }

    public void setEngagement(Double engagementScore) {
        this.engagementScore = engagementScore;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getImprovements() {
        return improvements;
    }

    public void setImprovements(List<String> improvements) {
        this.improvements = improvements;
    }

    public List<String> getSuggestedTopics() {
        return suggestedTopics;
    }

    public void setSuggestedTopics(List<String> suggestedTopics) {
        this.suggestedTopics = suggestedTopics;
    }

    public String getSeoAnalysis() {
        return seoAnalysis;
    }

    public void setSeoAnalysis(String seoAnalysis) {
        this.seoAnalysis = seoAnalysis;
    }

    public String getThumbnailSuggestions() {
        return thumbnailSuggestions;
    }

    public void setThumbnailSuggestions(String thumbnailSuggestions) {
        this.thumbnailSuggestions = thumbnailSuggestions;
    }

    public String getTargetedAudience() {
        return targetedAudience;
    }

    public void setTargetedAudience(String targetedAudience) {
        this.targetedAudience = targetedAudience;
    }

    public LocalDateTime getAnalyzedDate() {
        return analyzedDate;
    }

    public void setAnalyzedDate(LocalDateTime analyzedDate) {
        this.analyzedDate = analyzedDate;
    }

    public String getModelUsed() {
        return modelUsed;
    }

    public void setModelUsed(String modelUsed) {
        this.modelUsed = modelUsed;
    }



}
