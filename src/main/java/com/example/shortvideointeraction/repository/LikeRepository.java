package com.example.shortvideointeraction.repository;

import com.example.shortvideointeraction.model._Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeRepository extends CrudRepository<_Like, Integer>, PagingAndSortingRepository<_Like, Integer> {
    boolean existsByUserIdAndVideoId(Integer userId, Integer videoId);

    @Modifying
    @Transactional
    void deleteByUserIdAndVideoId(Integer userId, Integer videoId);

    Page<_Like> findAllByUserId(Integer userId, Pageable pageable);

    Iterable<_Like> findByUserId(Integer userId);

}
