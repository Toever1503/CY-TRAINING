package shiki.controller;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface IUpload {
    Object upload(MultipartFile... files);

    Object upload(File... files);

    Object upload(Map<String, InputStream> files);

    Object update(MultipartFile... files);

    Object update(File... files);

    Object update(Map<String, InputStream> files);


    boolean delete(String... files);

    boolean isAlreadyExist(String fileName);

    default boolean hasMoreOne(Object[] files) {
        return files.length > 0;
    }

    default boolean hasMoreOne(Map<?, ?> files) {
        return files.size() > 0;
    }

    default boolean hasMoreOne(Collection<Object> files) {
        return files.size() > 0;
    }

    default String addSuffix(String file) {
        for (int i = 0; i < 1000; i++) {

        }
    }

}
