package com.example.shortvideointeraction.repository;

import com.example.shortvideointeraction.model.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FavoriteRepository extends CrudRepository<Favorite, Integer>, PagingAndSortingRepository<Favorite, Integer> {
    boolean existsByUserIdAndVideoId(Integer userId, Integer videoId);

    @Modifying
    @Transactional
    void deleteByUserIdAndVideoId(Integer userId, Integer videoId);

    Page<Favorite> findAllByUserId(Integer userId, Pageable pageable);

    Iterable<Favorite> findByUserId(Integer userId);

}
