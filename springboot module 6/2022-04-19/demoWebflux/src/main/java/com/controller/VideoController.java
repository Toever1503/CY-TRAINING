package com.controller;

import com.entity.Video;
import com.repository.VideoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/videos")
public class VideoController {

    private final VideoRepository videoRepository;


    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @GetMapping
    public Object videos(Pageable page) {
        Flux<Video> flux = this.videoRepository.findAllByTitleLike("%%", page);

        return flux.toStream().collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public Mono<Video> video(@PathVariable Long id) {
        Mono<Video> mono =  this.videoRepository.findById(id);

        return mono;
    }

    @PostMapping
    public Mono<Video> createVideo(@RequestBody Video video) {
        Mono<Video> mono = this.videoRepository.save(video);
        Video videos = mono.block();
        return mono;
    }

    @DeleteMapping("{id}")
    public Mono delete(@PathVariable Long id) {
        return this.videoRepository.deleteById(id);
    }
}
