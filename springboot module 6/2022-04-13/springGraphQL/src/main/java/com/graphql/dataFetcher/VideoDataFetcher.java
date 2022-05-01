package com.graphql.dataFetcher;

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
import graphql.schema.SelectedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class VideoDataFetcher {
    private final VideoRepository videoRepository;
    private final StudioRepository studioRepository;
    private final StaffRepository staffRepository;

    public VideoDataFetcher(VideoRepository videoRepository, StudioRepository studioRepository, StaffRepository staffRepository) {
        this.videoRepository = videoRepository;
        this.studioRepository = studioRepository;
        this.staffRepository = staffRepository;
    }

    public Video video(DataFetchingEnvironment env) {
        Video video = videoRepository.findById(Long.valueOf(env.getArgument("id"))).orElse(null);

        // ignore selectionSet, field, vars
        if (video == null) return null;
        setConnection(video, env);
        return video;
    }

    public List<Video> videos(DataFetchingEnvironment env) {
        Page<Video> videos = this.videoRepository.findAll(PageRequest.of(
                Integer.valueOf(env.getArgument("page")),
                Integer.valueOf(env.getArgument("perPage"))));
        videos.getContent().forEach(video -> {
            setConnection(video, env);
        });
        return videos.getContent();
    }

    void setConnection(Video video, DataFetchingEnvironment env) {
        Map<String, List<SelectedField>> fieldSelection = env.getSelectionSet().getFieldsGroupedByResultKey();

        List<SelectedField> staffSelection = fieldSelection.get("staffs");
        List<SelectedField> studioSelection = fieldSelection.get("studios");

        if (staffSelection != null) {
            video.setStaffs(getStaffConnection(video.getId(), GraphqlUtil.getPageable(staffSelection.get(0).getArguments())));
        }
        if (studioSelection != null) {
            video.setStudios(getStudioConnection(video.getId(), GraphqlUtil.getPageable(studioSelection.get(0).getArguments())));
        }
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
