package com.example.shortvideointeraction.controller;

import com.example.shortvideointeraction.jsonRequest.UserIdPageJsonRequest;
import com.example.shortvideointeraction.model.Video;
import com.example.shortvideointeraction.model._Like;
import com.example.shortvideointeraction.other.CustomPage;
import com.example.shortvideointeraction.other.VideoReturnType;
import com.example.shortvideointeraction.repository.LikeRepository;
import com.example.shortvideointeraction.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/svi")
public class LikeController {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping(path="/exists/like")
    public @ResponseBody boolean existsLike(@RequestParam Integer userId, @RequestParam Integer videoId) {
        return likeRepository.existsByUserIdAndVideoId(userId, videoId);
    }

    @PostMapping(path = "/add/like")
    public @ResponseBody String addLike(@RequestParam Integer userId, @RequestParam Integer videoId) {
        _Like like = new _Like();
        like.setUserId(userId);
        like.setVideoId(videoId);
        like.setAddTime(new Date());
        likeRepository.save(like);
        return "Like added";
    }

    @DeleteMapping(path = "/delete/like")
    public @ResponseBody String deleteLike(@RequestParam Integer userId, @RequestParam Integer videoId) {
        likeRepository.deleteByUserIdAndVideoId(userId, videoId);
        return "Like deleted";
    }

    @GetMapping(path="/list/like")
    public @ResponseBody CustomPage listLike(@RequestBody UserIdPageJsonRequest userIdPageJsonRequest) {
        Integer page = userIdPageJsonRequest.getPage();
        Integer size = userIdPageJsonRequest.getSize();
        Integer userId = userIdPageJsonRequest.getUserId();
        Pageable pageable = PageRequest.of(page, size);
        Page<_Like> _page = likeRepository.findAllByUserId(userId, pageable);

        Iterable<_Like> likeIterable = likeRepository.findByUserId(userId);

        CustomPage customPage = new CustomPage();
        customPage.setNumberOfElements(_page.getNumberOfElements());
        customPage.setPageNumber(_page.getPageable().getPageNumber());
        customPage.setPageSize(_page.getPageable().getPageSize());
        List<VideoReturnType> newContent = new ArrayList<>();
        List<Integer> videoIdList = new ArrayList<>();
        for (_Like like : likeIterable)
        {
            videoIdList.add(like.getVideoId());
        }
        Iterable<Video> videoIterable = videoRepository.findAllById(videoIdList);
        for(Video video : videoIterable)
        {
            VideoReturnType videoReturnType = new VideoReturnType();
            videoReturnType.setTitle(video.getTitle());
            newContent.add(videoReturnType);
        }
        customPage.setContent(newContent);
        return customPage;
//        return _page;
    }
}
