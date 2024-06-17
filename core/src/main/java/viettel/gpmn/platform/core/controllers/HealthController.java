package viettel.gpmn.platform.core.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.gpmn.platform.core.data.response.Response;
import viettel.gpmn.platform.core.enums.KafkaTopic;
import viettel.gpmn.platform.core.services.KafkaService;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/health"})
public class HealthController extends BaseController {

    private EntityManager entityManager;
    private RedisTemplate<String, Object> redisTemplate;

    private KafkaService kafkaService;

    @GetMapping
    public Response healthCheck() {
        return Response.ok();
    }

    @GetMapping(value = {"/db"})
    public Response healthCheckDB() {
        return Response.ok(entityManager.unwrap(Session.class).isConnected());
    }

    @GetMapping(value = {"/redis"})
    public Response healthCheckRedis() {
        redisTemplate.opsForValue().get("");
        return Response.ok();
    }

    @GetMapping(value = {"/kafka"})
    public Response healthCheckKafka() {
        kafkaService.send(KafkaTopic.HEALTH_CHECK, "");
        return Response.ok();
    }

}
