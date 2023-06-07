package com.example.shortvideointeraction.controller;

import com.example.shortvideointeraction.jsonRequest.PageJsonRequest;
import com.example.shortvideointeraction.model.Video;
import com.example.shortvideointeraction.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(path="/svi")
public class VideoController {
    @Autowired
    private VideoRepository videoRepository;
    @PostMapping(path = "/add/video")
    public @ResponseBody String addVideo(@RequestParam Integer videoId, @RequestParam String title)
    {
        Video video = new Video();
        video.setVideoId(videoId);
        video.setTitle(title);
        video.setAddTime(new Date());
        videoRepository.save(video);
        return "Video added";
    }

    @DeleteMapping(path = "/delete/video")
    public @ResponseBody String deleteVideo(@RequestParam Integer videoId) {
        videoRepository.deleteById(videoId);
        return "Video deleted";
    }

    @GetMapping(path = "/all/video")
    public @ResponseBody Iterable<Video> allVideo(@RequestBody PageJsonRequest pageJsonRequest)
    {
        Integer page = pageJsonRequest.getPage();
        Integer size = pageJsonRequest.getSize();
        Pageable pageable = PageRequest.of(page, size);
        return videoRepository.findAll(pageable);
    }

    @GetMapping(path = "/detail/video")
    public @ResponseBody Video detailVideo(@RequestParam Integer videoId)
    {
        return videoRepository.findById(videoId).get();
    }
}
