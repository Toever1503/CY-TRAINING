package com.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.domain.Staff;
import com.domain.Studio;
import com.domain.Video;
import com.graphql.GraphqlUtil;
import com.graphql.model.StaffConnection;
import com.graphql.model.StudioConnection;
import com.repository.StaffRepository;
import com.repository.StudioRepository;
import com.repository.VideoRepository;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class VideoQueryResolver implements GraphQLQueryResolver {
    private final VideoRepository videoRepository;
    private final StudioRepository studioRepository;
    private final StaffRepository staffRepository;

    public VideoQueryResolver(VideoRepository videoRepository, StudioRepository studioRepository, StaffRepository staffRepository) {
        this.videoRepository = videoRepository;
        this.studioRepository = studioRepository;
        this.staffRepository = staffRepository;
    }

    public List<Video> Videos(int page, int perPage, DataFetchingEnvironment env) {
        Page<Video> videos = this.videoRepository.findAll(PageRequest.of(page, perPage));
        videos.getContent().forEach(video -> {
            setConnection(video, env);
        });
        return videos.getContent();
    }

    public Video Video(Long id, DataFetchingEnvironment env) {
        Video video = videoRepository.findById(1l).orElse(null);
        if (video == null) return null;
        setConnection(video, env);
        return video;
    }

    void setConnection(Video video, DataFetchingEnvironment env) {
//        Map<String, Object> staffSelection = env.getSelectionSet().getArguments().get("staffs");
//        Map<String, Object> studioSelection = env.getSelectionSet().getArguments().get("studios");
//        if (staffSelection != null) {
//            video.setStaffs(getStaffConnection(video.getId(), GraphqlUtil.getPageable(staffSelection)));
//        }
//        if (studioSelection != null) {
//            video.setStudios(getStudioConnection(video.getId(), GraphqlUtil.getPageable(studioSelection)));
//        }
    }

    StaffConnection getStaffConnection(Long vId, Pageable page) {
        Page<Staff> staffPage = this.staffRepository.findAllStaffsByVideoId(vId, page);
        return new StaffConnection(staffPage.getContent(), GraphqlUtil.getPageInfoFromPage(staffPage));
    }

    StudioConnection getStudioConnection(Long vId, Pageable page) {
        Page<Studio> studioPage = this.studioRepository.findAllStaffsByVideoId(vId, page);
        return new StudioConnection(studioPage.getContent(), GraphqlUtil.getPageInfoFromPage(studioPage));
    }
}
