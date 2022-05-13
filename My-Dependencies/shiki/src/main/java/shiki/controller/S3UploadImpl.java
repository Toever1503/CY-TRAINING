package shiki.controller;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class S3UploadImpl implements IUpload {
    private final AmazonS3 s3Client;
    private boolean isFlowKey = false;
    private String bucket;

    public S3UploadImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public S3UploadImpl setBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public S3UploadImpl setFlowKey(boolean isFlowKey) {
        this.isFlowKey = isFlowKey;
        return this;
    }

    @Override
    public Object upload(MultipartFile... files) {
        if (!this.hasMoreOne(files))
            return false;
        return null;
    }

    @Override
    public Object upload(File... files) {
        if (!this.hasMoreOne(files))
            return false;
        return null;
    }

    @Override
    public Object upload(Map<String, InputStream> files) {
        if (!this.hasMoreOne(files))
            return false;
        files.forEach((key, value) -> {
            if (this.isAlreadyExist(key)) {
                this.s3Client.putObject(this.bucket, key, value, null);
            }
        });
        return null;
    }


    @Override
    public Object update(MultipartFile... files) {
        if (!this.hasMoreOne(files))
            return false;
        return null;
    }

    @Override
    public Object update(File... files) {
        if (!this.hasMoreOne(files))
            return false;
        for (File file : files) {
            if (this.isAlreadyExist(file.getName())) {

            }
        }
        return null;
    }

    @Override
    public Object update(Map<String, InputStream> files) {
        if (!this.hasMoreOne(files))
            return false;
        files.forEach((key, value) -> {
            if (this.isAlreadyExist(key)) {
                this.s3Client.putObject(this.bucket, key, value, null);
            }
        });
        return null;
    }

    @Override
    public boolean delete(String... files) {
        if (!this.hasMoreOne(files))
            return false;


        return true;
    }

    @Override
    public boolean isAlreadyExist(String fileName) {
        return this.bucket == null ? null : this.s3Client.doesObjectExist(this.bucket, fileName);
    }
}
