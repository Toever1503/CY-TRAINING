package com.service;

import com.entities.Video;
import com.entities.model.VideoModel;
import com.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final CharRepository charRepository;
    private final CategoryRepository categoryRepository;
    private final StaffRepository staffRepository;
    private final StudioRepository studioRepository;
    private final TagRepository tagRepository;

    public VideoService(VideoRepository videoRepository, CharRepository charRepository, CategoryRepository categoryRepository, StaffRepository staffRepository, StudioRepository studioRepository, TagRepository tagRepository) {
        this.videoRepository = videoRepository;
        this.charRepository = charRepository;
        this.categoryRepository = categoryRepository;
        this.staffRepository = staffRepository;
        this.studioRepository = studioRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional
    public Video save(VideoModel videoModel) {
        Video v = toEntity(videoModel);
        v.setStudios(studioRepository.findAllByIdIn(videoModel.getStudios()));
        v.setVCategories(categoryRepository.findAllByIdIn(videoModel.getVCategories()));
        v.setVStaffs(staffRepository.findAllByIdIn(videoModel.getVStaffs()));
        return this.videoRepository.save(v);
    }

    public VideoModel modelGet(Long id){
        return toModel(videoRepository.findById(id).orElse(null));
    }

    private Video toEntity(VideoModel videoModel) {
        return Video.builder()
                .id(videoModel.getId())
                .title(videoModel.getTitle())
                .poster(videoModel.getPoster())
                .description(videoModel.getDescription())
                .views(videoModel.getViews())
                .country(videoModel.getCountry()).build();
    }

    private VideoModel toModel(Video video) {
        if(video == null) return null;
        return VideoModel.builder()
                .id(video.getId())
                .title(video.getTitle())
                .poster(video.getPoster())
                .description(video.getDescription())
                .views(video.getViews())
                .country(video.getCountry())
                .studios(video.getStudios().stream().map(studio -> studio.getId()).collect(Collectors.toList()))
                .vCategories(video.getVCategories().stream().map(category -> category.getId()).collect(Collectors.toList()))
                .vStaffs(video.getVStaffs().stream().map(staff -> staff.getId()).collect(Collectors.toList()))
//                .vTags(video.getVTags().stream().map(tag -> tag.getId()).collect(Collectors.toList()))
                .build();
    }
}
