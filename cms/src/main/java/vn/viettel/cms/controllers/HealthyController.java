package vn.viettel.cms.controllers;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/healthy"})
public class HealthyController extends BaseController {

    private final EntityManager entityManager;

    @GetMapping
    public Response healthCheck() throws RuntimeException {
        return Response.ok(entityManager.unwrap(Session.class).isConnected());
    }

}
