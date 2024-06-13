package viettel.gpmn.platform.retail.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import viettel.gpmn.platform.core.services.BaseService;
import viettel.gpmn.platform.retail.entities.SupplierUnits;
import viettel.gpmn.platform.retail.repositories.SupplierUnitRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierUnitService extends BaseService {

    private SupplierUnitRepository supplierUnitRepository;

    public List<SupplierUnits> getList() {
        return supplierUnitRepository.findAll();
    }

}
