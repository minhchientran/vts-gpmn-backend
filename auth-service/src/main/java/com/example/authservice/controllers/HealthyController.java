package com.example.authservice.controllers;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;

@RestController
@RequestMapping(value = {"/healthy"})
public class HealthyController extends BaseController {

    private final EntityManager entityManager;

    public HealthyController(
            EntityManager entityManager
    ) {
        this.entityManager = entityManager;
    }
    @GetMapping
    public Response healthCheck() throws RuntimeException {
        return Response.ok(entityManager.unwrap(Session.class).isConnected());
    }

}
