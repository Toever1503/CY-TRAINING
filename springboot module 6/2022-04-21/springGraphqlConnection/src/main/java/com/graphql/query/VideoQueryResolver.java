package com.graphql.query;

import com.domain.Video;
import com.graphql.connection.*;
import com.repository.VideoRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class VideoQueryResolver implements GraphQLQueryResolver {

    private final VideoRepository videoRepository;
    private final ConnectionQuery connectionQuery;

    public VideoQueryResolver(VideoRepository videoRepository, ConnectionQuery connectionQuery) {
        this.videoRepository = videoRepository;
        this.connectionQuery = connectionQuery;
    }

    public CompletableFuture<Video> video(long id, DataFetchingEnvironment env) {
        try {
            DataLoader dataLoader = env.getDataLoader("video");
            return dataLoader.load(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("ended get video with current thread: " + Thread.currentThread().getId());
        }
        return null;
    }

    public CustomConnection<Video> videos(String q, int page, int perPage, DataFetchingEnvironment env) {
        Page<Video> pageData = null;
        Pageable pageable = PageRequest.of(page, perPage);
        if (q == null || q.isEmpty())
            pageData = this.videoRepository.findAll(pageable);
        else
            pageData = this.videoRepository.search(q, pageable);
        return new DefaultCustomConnection<Video>(new ConnectionQuery.Edges<Video>(pageData.getContent()).getEdges(), ConnectionQuery.extractPageInfo(pageData));
    }
}
