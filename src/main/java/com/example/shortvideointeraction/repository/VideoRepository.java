package com.example.shortvideointeraction.repository;

import com.example.shortvideointeraction.model.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VideoRepository extends CrudRepository<Video, Integer>, PagingAndSortingRepository<Video, Integer> {
}
