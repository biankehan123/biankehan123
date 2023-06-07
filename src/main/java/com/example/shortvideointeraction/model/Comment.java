package com.example.shortvideointeraction.model;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer videoId;
    private String commentContent;
    private Date commentTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getCommentContent(){
        return commentContent;
    }

    public void setCommentContent(String commentContent){
        this.commentContent = commentContent;
    }

    public Date getCommentTime(){
        return commentTime;
    }

    public void setCommentTime(Date commentTime){
        this.commentTime = commentTime;
    }
}
