package viettel.gpmn.platform.retail.services;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import viettel.gpmn.platform.core.configs.tenant.TenantManager;
import viettel.gpmn.platform.core.services.BaseService;
import viettel.gpmn.platform.retail.entities.SupplierDatabase;
import viettel.gpmn.platform.retail.repositories.SupplierDatabaseRepository;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class TenantService extends BaseService {

    private TenantManager multiTenantManager;
    private SupplierDatabaseRepository supplierDatabaseRepository;

    @PostConstruct
    public void init() {
        List<SupplierDatabase> listSupplierDatabase = supplierDatabaseRepository.findAll();
        listSupplierDatabase.forEach(
                supplierDatabase -> multiTenantManager.addTenant(
                        supplierDatabase.getSupplierId(), supplierDatabase.getUrl(),
                        supplierDatabase.getUsername(), supplierDatabase.getPassword()
                )
        );
    }

    @KafkaListener(topics = "#{T(viettel.gpmn.platform.core.enums.KafkaTopic).BROADCAST_NEW_DATABASE.name()}")
    public void listenBroadcastNewDatabase(
            String message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws IOException {
        log.info("[KafkaListener] [{}] [{}]", topic, message);
        SupplierDatabase supplierDatabase = this.objectMapper.readValue(message, SupplierDatabase.class);
        multiTenantManager.addTenant(
                supplierDatabase.getSupplierId(), supplierDatabase.getUrl(),
                supplierDatabase.getUsername(), supplierDatabase.getPassword()
        );
    }
}
