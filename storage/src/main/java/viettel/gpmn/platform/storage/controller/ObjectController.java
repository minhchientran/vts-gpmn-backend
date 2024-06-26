package viettel.gpmn.platform.storage.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import viettel.gpmn.platform.storage.service.MinIOService;

@Path("/file")
public class ObjectController {

    @Inject
    MinIOService minIOService;

    @GET
    @Path("/{bucket}/{object}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFileById(
            @PathParam("bucket") String bucket,
            @PathParam("object") String object)
    {
        return minIOService.getFileData(bucket, object);
    }

}
