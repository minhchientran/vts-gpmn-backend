package viettel.gpmn.platform.storage.service;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.InputStream;

@ApplicationScoped
public class MinIOService {

    private MinioClient connect() {
        return MinioClient
                .builder()
                .endpoint("http://10.30.174.212:8773")
                .credentials("admin", "123456a@A")
                .build();
    }

    public Response getFileData(String bucketName, String filePath) {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(filePath)
                .build()
                ;

        MinioClient minioClient = connect();
        InputStream inputStream = null;
        try {
            inputStream = minioClient.getObject(getObjectArgs);
            Response.ResponseBuilder builder = Response.ok(inputStream);
            builder.header("Content-Type", "image/png");
            builder.header("Content-Disposition", "attachment; filename=" + filePath);
            return builder.build();
        }
        catch (Exception exception) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception ignored) {}
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
