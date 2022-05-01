package com.graphql.query;

import com.domain.Video;
import com.graphql.connection.*;
import com.repository.VideoRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class VideoQueryResolver implements GraphQLQueryResolver {

    private final VideoRepository videoRepository;
    private final ConnectionQuery connectionQuery;

    public VideoQueryResolver(VideoRepository videoRepository, ConnectionQuery connectionQuery) {
        this.videoRepository = videoRepository;
        this.connectionQuery = connectionQuery;
    }

    public Video video(long id, DataFetchingEnvironment env) {
        return this.videoRepository.findById(id).orElse(null);
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
