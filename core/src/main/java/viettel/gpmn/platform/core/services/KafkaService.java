package viettel.gpmn.platform.core.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import viettel.gpmn.platform.core.enums.KafkaTopic;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaService extends BaseService {

    private KafkaTemplate<String, String> kafkaTemplate;

    @SneakyThrows
    public void send(KafkaTopic topic, Object object) {

        String message = objectMapper.writeValueAsString(object);

        kafkaTemplate.send(topic.name(), message)
                .thenAccept(result ->
                        log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]"))
                .exceptionally(exception -> {
                    exception.printStackTrace();
                    log.info("Unable to send message=[" + message + "] due to : " + exception.getMessage());
                    return null;
                })
                ;
    }
}
