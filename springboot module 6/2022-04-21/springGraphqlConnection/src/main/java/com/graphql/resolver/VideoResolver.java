package com.graphql.resolver;

import com.domain.Staff;
import com.domain.Video;
import com.graphql.CustomDataloaderKey;
import com.graphql.connection.CustomConnection;
import com.graphql.connection.CustomPageable;
import com.graphql.query.ConnectionQuery;
import com.repository.StaffRepository;
import com.repository.VideoRepository;
import graphql.GraphqlErrorException;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Component
public class VideoResolver implements GraphQLResolver<Video> {
    private final VideoRepository videoRepository;
    private final StaffRepository staffRepository;

    public VideoResolver(VideoRepository videoRepository, StaffRepository staffRepository) {
        this.videoRepository = videoRepository;
        this.staffRepository = staffRepository;
    }

    public CompletableFuture<CustomConnection<Staff>> getStaffs(Video video, CustomPageable page, DataFetchingEnvironment env) {
        try {
            DataLoader staffLoader = env.getDataLoader("staffsByVideoLoader");
            return staffLoader.load(List.of(video.getId(), page));
//            return ConnectionQuery.createConnection(staffRepository.findAllStaffsByVideoId(video.getId(), page.toPageable()));
        } catch (Exception e) {
            throw GraphqlErrorException.newErrorException().message("Error while fetching staffs field with sort property by: " + page.getSort().getProperties()).build();
        } finally {
            System.out.println("ended video query for staffs field with current Thread: " + Thread.currentThread().getId());
        }
    }
}
