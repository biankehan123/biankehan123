package com.example.shortvideointeraction.jsonRequest;

public class UserIdPageJsonRequest {
    private Integer page;
    private Integer size;
    private Integer userId;

    public Integer getPage()
    {
        return page;
    }

    public void setSize(Integer size)
    {
        this.size = size;
    }

    public Integer getSize()
    {
        return size;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
