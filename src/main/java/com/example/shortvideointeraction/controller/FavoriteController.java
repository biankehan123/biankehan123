package com.example.shortvideointeraction.controller;

import com.example.shortvideointeraction.jsonRequest.UserIdPageJsonRequest;
import com.example.shortvideointeraction.model.Favorite;
import com.example.shortvideointeraction.model.Video;
import com.example.shortvideointeraction.model._Like;
import com.example.shortvideointeraction.other.CustomPage;
import com.example.shortvideointeraction.other.VideoReturnType;
import com.example.shortvideointeraction.repository.FavoriteRepository;
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
public class FavoriteController {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private VideoRepository videoRepository;

    @GetMapping(path="/exists/favorite")
    public @ResponseBody boolean existsFavorite(@RequestParam Integer userId, @RequestParam Integer videoId) {
        return favoriteRepository.existsByUserIdAndVideoId(userId, videoId);
    }

    @PostMapping(path = "/add/favorite")
    public @ResponseBody String addFavorite(@RequestParam Integer userId, @RequestParam Integer videoId) {
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setVideoId(videoId);
        favorite.setAddTime(new Date());
        favoriteRepository.save(favorite);
        return "Favorite added";
    }

    @DeleteMapping(path = "/delete/favorite")
    public @ResponseBody String deleteFavorite(@RequestParam Integer userId, @RequestParam Integer videoId) {
        favoriteRepository.deleteByUserIdAndVideoId(userId, videoId);
        return "Favorite deleted";
    }

    @GetMapping(path="/list/favorite")
    public @ResponseBody CustomPage listFavorite(@RequestBody UserIdPageJsonRequest userIdPageJsonRequest) {
        Integer page = userIdPageJsonRequest.getPage();
        Integer size = userIdPageJsonRequest.getSize();
        Integer userId = userIdPageJsonRequest.getUserId();
        Pageable pageable = PageRequest.of(page, size);
        Page<Favorite> _page = favoriteRepository.findAllByUserId(userId, pageable);

        Iterable<Favorite> favoriteIterable = favoriteRepository.findByUserId(userId);

        CustomPage customPage = new CustomPage();
        customPage.setNumberOfElements(_page.getNumberOfElements());
        customPage.setPageNumber(_page.getPageable().getPageNumber());
        customPage.setPageSize(_page.getPageable().getPageSize());
        List<VideoReturnType> newContent = new ArrayList<>();
        List<Integer> videoIdList = new ArrayList<>();
        for (Favorite favorite : favoriteIterable)
        {
            videoIdList.add(favorite.getVideoId());
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
    }
}
