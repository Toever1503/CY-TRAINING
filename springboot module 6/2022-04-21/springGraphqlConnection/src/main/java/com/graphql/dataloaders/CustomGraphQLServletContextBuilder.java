package com.graphql.dataloaders;

import com.domain.Video;
import com.graphql.connection.CustomConnection;
import com.graphql.connection.CustomPageable;
import com.graphql.query.ConnectionQuery;
import com.repository.StaffRepository;
import com.repository.VideoRepository;
import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CustomGraphQLServletContextBuilder implements GraphQLServletContextBuilder {
    //    private  Map<String, DataLoader<?, ?>> dataLoaders;

    private final StaffRepository staffRepository;
    private final VideoRepository videoRepository;
    private final DataLoaderRegistry dataLoaderRegistry;

    public CustomGraphQLServletContextBuilder(StaffRepository staffRepository, VideoRepository videoRepository) {
        this.staffRepository = staffRepository;
        this.videoRepository = videoRepository;
        dataLoaderRegistry = new DataLoaderRegistry();
        dataLoaderRegistry.register("staffsByVideoLoader", new DataLoader<Object, CustomConnection>(
                key -> CompletableFuture.supplyAsync(() -> {
                            Object keyOf = key.get(0);
                            return List.of(ConnectionQuery
                                    .createConnection(staffRepository
                                            .findAllStaffsByVideoId((Long) ((List<Object>) keyOf).get(0),
                                                    ((CustomPageable) ((List<Object>) keyOf).get(1)).toPageable())));
                        }
                )));
        dataLoaderRegistry.register("video", new DataLoader<Long, Video>(
                keyV -> CompletableFuture.supplyAsync(() -> List.of(videoRepository.findById((Long) keyV.get(0)).orElse(null)))
        ));
//        dataLoaders.forEach(dataLoaderRegistry::register);
    }

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return DefaultGraphQLServletContext.createServletContext()
                .with(dataLoaderRegistry)
                .with(httpServletRequest)
                .with(httpServletResponse)
                .build();
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext(dataLoaderRegistry, null)
                .with(session)
                .with(handshakeRequest)
                .build();
    }

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext(dataLoaderRegistry, null);
    }

}
