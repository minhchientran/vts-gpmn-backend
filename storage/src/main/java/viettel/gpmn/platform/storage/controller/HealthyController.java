package viettel.gpmn.platform.storage.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/healthy")
public class HealthyController {

    @GET
    @Path("")
    public String healthCheck() {
        return "OK";
    }

}
