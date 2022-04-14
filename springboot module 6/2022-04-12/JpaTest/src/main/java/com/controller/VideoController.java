package com.controller;

import com.entities.Video;
import com.entities.model.VideoModel;
import com.repository.VideoRepository;
import com.service.VideoService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("anime/video")
public class VideoController {
    private static final long serialVersionUID = 1L;
    private final VideoRepository videoRepository;
    private final VideoService videoService;

    public VideoController(VideoRepository videoRepository, VideoService videoService) {
        this.videoRepository = videoRepository;
        this.videoService = videoService;
    }

    @GetMapping
    public Object getAll(Pageable page) {
        return videoRepository.findAll(page);
    }

    @GetMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    @GetMapping("model/{id}")
    public Object getModelById(@PathVariable Long id) {
        return this.videoService.modelGet(id);
    }

    @PatchMapping("{id}")
    public Object update(@PathVariable Long id, @RequestBody VideoModel video) {
        System.out.println("update->" + video);
        video.setId(id);
        return this.videoService.save(video);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Long id) {
        this.videoRepository.deleteById(id);
        return true;
    }
}