package com.example.shortvideointeraction.repository;

import com.example.shortvideointeraction.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer>, PagingAndSortingRepository<Comment, Integer> {
    Page<Comment> findAllByVideoIdOrderByCommentTimeDesc(Integer videoId, Pageable pageable);

    @Modifying
    @Transactional
    void deleteByUserIdAndVideoId(Integer userId, Integer videoId);
}
