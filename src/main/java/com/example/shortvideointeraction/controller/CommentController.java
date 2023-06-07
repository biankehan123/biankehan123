package com.example.shortvideointeraction.controller;

import com.example.shortvideointeraction.jsonRequest.UserIdPageJsonRequest;
import com.example.shortvideointeraction.jsonRequest.VideoIdPageJsonRequest;
import com.example.shortvideointeraction.model.Comment;
import com.example.shortvideointeraction.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/svi")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping(path = "/add/comment")
    public @ResponseBody String addComment(@RequestParam Integer userId, @RequestParam Integer videoId,
                                           @RequestParam String commentContent) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setVideoId(videoId);
        comment.setCommentContent(commentContent);
        comment.setCommentTime(new Date());
        commentRepository.save(comment);
        return "Comment added";
    }

    @GetMapping(path="/list/comment")
    public @ResponseBody Page<Comment> listComment(@RequestBody VideoIdPageJsonRequest videoIdPageJsonRequest) {
        Integer page = videoIdPageJsonRequest.getPage();
        Integer size = videoIdPageJsonRequest.getSize();
        Integer videoId = videoIdPageJsonRequest.getVideoId();
        Pageable pageable = PageRequest.of(page, size);
        return commentRepository.findAllByVideoIdOrderByCommentTimeDesc(videoId, pageable);
    }

    @DeleteMapping(path = "/delete/comment")
    public @ResponseBody String deleteComment(@RequestParam Integer userId, @RequestParam Integer videoId) {
        commentRepository.deleteByUserIdAndVideoId(userId, videoId);
        return "Comment deleted";
    }
}
