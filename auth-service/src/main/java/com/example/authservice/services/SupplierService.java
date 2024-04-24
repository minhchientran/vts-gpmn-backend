package com.example.authservice.services;

import com.example.authservice.data.suppliers.SupplierData;
import com.example.authservice.data.suppliers.SupplierModuleData;
import com.example.authservice.entities.SupplierInfo;
import com.example.authservice.entities.SupplierModuleMap;
import com.example.authservice.entities.Suppliers;
import com.example.authservice.repositories.SupplierDatabaseRepository;
import com.example.authservice.repositories.SupplierInfoRepository;
import com.example.authservice.repositories.SupplierModuleMapRepository;
import com.example.authservice.repositories.SuppliersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.viettel.core.services.BaseService;

@Service
public class SupplierService extends BaseService {

    private final SuppliersRepository suppliersRepository;
    private final SupplierInfoRepository supplierInfoRepository;
    private final SupplierDatabaseRepository supplierDatabaseRepository;
    private final SupplierModuleMapRepository supplierModuleMapRepository;

    public SupplierService(
            SuppliersRepository suppliersRepository,
            SupplierInfoRepository supplierInfoRepository,
            SupplierDatabaseRepository supplierDatabaseRepository,
            SupplierModuleMapRepository supplierModuleMapRepository
    ) {
        this.suppliersRepository = suppliersRepository;
        this.supplierInfoRepository = supplierInfoRepository;
        this.supplierDatabaseRepository = supplierDatabaseRepository;
        this.supplierModuleMapRepository = supplierModuleMapRepository;
    }
    @Transactional
    public void createSupplier(SupplierData supplierData) {
        Suppliers suppliers = modelMapper.map(supplierData, Suppliers.class);
        suppliersRepository.save(suppliers);
        SupplierInfo supplierInfo = modelMapper.map(supplierData, SupplierInfo.class);
        supplierInfoRepository.save(supplierInfo);
    }

    @Transactional
    public void createSupplierModule(SupplierModuleData supplierModuleData) {
        SupplierModuleMap supplierModuleMap = modelMapper.map(supplierModuleData, SupplierModuleMap.class);
        supplierModuleMapRepository.save(supplierModuleMap);
    }
}
