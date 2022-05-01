package com.repository;

import com.entity.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VideoRepository extends ReactiveCrudRepository<Video, Long> {
    Flux<Video> findAllByTitleLike(String q, Pageable page);

}
