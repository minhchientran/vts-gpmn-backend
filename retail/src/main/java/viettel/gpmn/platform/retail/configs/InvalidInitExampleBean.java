package viettel.gpmn.platform.retail.configs;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import viettel.gpmn.platform.core.configs.tenant.MultiTenantManager;
import viettel.gpmn.platform.retail.entities.SupplierDatabase;
import viettel.gpmn.platform.retail.repositories.SupplierDatabaseRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class InvalidInitExampleBean {

    private MultiTenantManager multiTenantManager;
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
}
