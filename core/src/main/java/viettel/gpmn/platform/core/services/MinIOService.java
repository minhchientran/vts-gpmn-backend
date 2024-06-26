package viettel.gpmn.platform.core.services;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class MinIOService  {

    @Value("${info.minio.url}")
    private String url;
    @Value("${info.minio.port}")
    private Integer port;
    @Value("${info.minio.secure}")
    private Boolean secure;
    @Value("${info.minio.username}")
    private String username;
    @Value("${info.minio.password}")
    private String password;
    @Value("${info.minio.file-size}")
    private Long fileSize;

    public MinioClient getClient() {
        return MinioClient.builder()
                .endpoint(url, port, secure)
                .credentials(username, password)
                .build();
    }

    @SneakyThrows
    public String WriteToMinIO(MultipartFile file, String bucketName, String filePath) {
        String orgFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        filePath += ".";
        filePath += orgFileName.split("\\.")[orgFileName.split("\\.").length - 1];
        try {
            MinioClient minioClient = getClient();
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filePath)
                            .stream(inputStream, -1, fileSize)
                            .build());

        } catch (MinioException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return filePath;
    }

    @SneakyThrows
    public void WriteToMinIO(InputStream inputStream, String bucketName, String filePath) {
        try {
            MinioClient minioClient = getClient();
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filePath)
                            .stream(inputStream, -1, fileSize)
                            .build());

        } catch (MinioException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public String getPreSignedUrl(String bucketName, String filePath) {
        String url = null;
        try {
            MinioClient minioClient = getClient();
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(filePath)
                            .build());
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        return url;
    }

    @SneakyThrows
    public InputStream getFromMinIO(String bucketName, String filePath) {
        try {
            MinioClient minioClient = getClient();
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (bucketExists) {
                return minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(bucketName)
                                .object(filePath)
                                .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @SneakyThrows
    public List<String> getListObjectNameInBucket(String bucketName) {
        MinioClient minioClient = getClient();
        List<String> listObjectName = new ArrayList<>();
        if (minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            Iterable<Result<Item>> listObject = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
            listObject.forEach(
                    itemResult -> {
                        try {
                            listObjectName.add(itemResult.get().objectName());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
            );
        }
        return listObjectName;
    }

    @SneakyThrows
    public void removeObjects(String bucketName, List<String> listObjectName) {
        MinioClient minioClient = getClient();
        if (minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            listObjectName.forEach(
                    objectName -> {
                        try {
                            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
    }

    @SneakyThrows
    public void makeBucket(String bucketName) {
        try {
            MinioClient minioClient = getClient();
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
