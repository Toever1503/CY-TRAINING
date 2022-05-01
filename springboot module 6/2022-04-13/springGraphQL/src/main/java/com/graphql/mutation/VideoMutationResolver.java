package com.graphql.mutation;


import com.domain.Video;
import com.repository.VideoRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

//@Component
public class VideoMutationResolver {
    private final VideoRepository videoRepository;

    public VideoMutationResolver(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video InsertOrUpdateVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video DeleteVideo(Long id) {
        Video video = videoRepository.findById(id).orElse(null);
        videoRepository.deleteById(id);
        return video;
    }

}
