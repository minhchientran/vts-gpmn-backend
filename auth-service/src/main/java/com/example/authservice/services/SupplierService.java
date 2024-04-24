package com.example.authservice.services;

import com.example.authservice.data.suppliers.SupplierCreateData;
import com.example.authservice.entities.Suppliers;
import com.example.authservice.repositories.SupplierDatabaseRepository;
import com.example.authservice.repositories.SupplierInfoRepository;
import com.example.authservice.repositories.SuppliersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.viettel.core.services.BaseService;

@Service
public class SupplierService extends BaseService {

    private final SuppliersRepository suppliersRepository;
    private final SupplierInfoRepository supplierInfoRepository;
    private final SupplierDatabaseRepository supplierDatabaseRepository;

    public SupplierService(
            SuppliersRepository suppliersRepository,
            SupplierInfoRepository supplierInfoRepository,
            SupplierDatabaseRepository supplierDatabaseRepository
    ) {
        this.suppliersRepository = suppliersRepository;
        this.supplierInfoRepository = supplierInfoRepository;
        this.supplierDatabaseRepository = supplierDatabaseRepository;
    }
    @Transactional
    public void createSupplier(SupplierCreateData supplierCreateData) {
        Suppliers suppliers = modelMapper.map(supplierCreateData, Suppliers.class);
        suppliersRepository.save(suppliers);
    }
}
