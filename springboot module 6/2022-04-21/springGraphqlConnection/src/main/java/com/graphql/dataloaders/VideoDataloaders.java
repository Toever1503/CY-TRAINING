package com.graphql.dataloaders;

import com.domain.Video;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.MappedBatchLoaderWithContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Component("videoDataloaders")
public class VideoDataloaders implements MappedBatchLoaderWithContext<String, Video> {

    @Override
    public CompletionStage<Map<String, Video>> load(Set<String> set, BatchLoaderEnvironment batchLoaderEnvironment) {
        return null;
    }
}
