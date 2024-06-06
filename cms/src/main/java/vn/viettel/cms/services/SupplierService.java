package vn.viettel.cms.services;

import vn.viettel.cms.data.suppliers.SupplierData;
import vn.viettel.cms.data.suppliers.SupplierModuleData;
import vn.viettel.cms.entities.SupplierInfo;
import vn.viettel.cms.entities.SupplierModuleMap;
import vn.viettel.cms.entities.Suppliers;
import vn.viettel.cms.repositories.SupplierDatabaseRepository;
import vn.viettel.cms.repositories.SupplierInfoRepository;
import vn.viettel.cms.repositories.SupplierModuleMapRepository;
import vn.viettel.cms.repositories.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.viettel.core.services.BaseService;

@Service
@AllArgsConstructor
public class SupplierService extends BaseService {

    private final SupplierRepository supplierRepository;
    private final SupplierInfoRepository supplierInfoRepository;
    private final SupplierDatabaseRepository supplierDatabaseRepository;
    private final SupplierModuleMapRepository supplierModuleMapRepository;

    @Transactional
    public void createSupplier(SupplierData supplierData) {
        Suppliers suppliers = modelMapper.map(supplierData, Suppliers.class);
        supplierRepository.save(suppliers);
        SupplierInfo supplierInfo = modelMapper.map(supplierData, SupplierInfo.class);
        supplierInfoRepository.save(supplierInfo);
    }

    @Transactional
    public void createSupplierModule(SupplierModuleData supplierModuleData) {
        SupplierModuleMap supplierModuleMap = modelMapper.map(supplierModuleData, SupplierModuleMap.class);
        supplierModuleMapRepository.save(supplierModuleMap);
    }
}
