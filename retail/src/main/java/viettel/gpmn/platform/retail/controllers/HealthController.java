package viettel.gpmn.platform.retail.controllers;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.response.Response;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/health"})
public class HealthController extends BaseController {

    private EntityManager entityManager;

    @GetMapping
    public Response healthCheck() throws RuntimeException {
        return Response.ok();
    }

    @GetMapping(value = {"/db"})
    public Response healthCheckDB() throws RuntimeException {
        return Response.ok(entityManager.unwrap(Session.class).isConnected());
    }

}
