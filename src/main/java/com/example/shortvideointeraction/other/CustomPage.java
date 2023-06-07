package com.example.shortvideointeraction.other;

import java.util.List;

public class CustomPage {
    private Integer numberOfElements;
    private Integer pageNumber;
    private Integer pageSize;
    private List<VideoReturnType> content;
    public Integer getNumberOfElements()
    {
        return numberOfElements;
    }
    public void setNumberOfElements(Integer numberOfElements)
    {
        this.numberOfElements = numberOfElements;
    }

    public Integer getPageNumber()
    {
        return pageNumber;
    }
    public void setPageNumber(Integer pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public List<VideoReturnType> getContent() {
        return content;
    }

    public void setContent(List<VideoReturnType> content) {
        this.content = content;
    }
}
