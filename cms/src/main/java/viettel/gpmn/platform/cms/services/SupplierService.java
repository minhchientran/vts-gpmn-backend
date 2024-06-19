package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel.gpmn.platform.cms.data.suppliers.SupplierData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierListData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierModuleData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierQuery;
import viettel.gpmn.platform.cms.entities.*;
import viettel.gpmn.platform.cms.repositories.*;
import viettel.gpmn.platform.core.enums.KafkaTopic;
import viettel.gpmn.platform.core.enums.Subsystem;
import viettel.gpmn.platform.core.services.BaseService;
import viettel.gpmn.platform.core.services.KafkaService;
import viettel.gpmn.platform.core.utilities.Constant;
import viettel.gpmn.platform.core.utilities.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupplierService extends BaseService {

    private Boolean isTest;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private SupplierRepository supplierRepository;
    private SupplierDatabaseRepository supplierDatabaseRepository;
    private SupplierModuleMapRepository supplierModuleMapRepository;
    private SupplierControlMapRepository supplierControlMapRepository;
    private UserRepository userRepository;
    private UserSupplierMapRepository userSupplierMapRepository;

    private ControlService controlService;
    private KafkaService kafkaService;

    public Page<SupplierListData> getListSuppliers(SupplierQuery supplierQuery, Pageable pageable) {
        Page<Suppliers> pageSupplier = supplierRepository.getListSupplier(supplierQuery, pageable);
        List<SupplierListData> listSupplierListData = this.modelMapper.map(pageSupplier.getContent(),
                new TypeToken<List<SupplierListData>>() {}.getType());
        return new PageImpl<>(listSupplierListData, pageable, pageSupplier.getTotalElements());
    }

    public SupplierData getSupplierDetail(String supplierId) {
        Suppliers suppliers = supplierRepository.findById(supplierId).orElseThrow();
        // TODO image
        return this.modelMapper.map(suppliers, SupplierData.class);
    }

    @Transactional
    public void createSupplier(SupplierData supplierData) {
        if (supplierData.getId() == null) {
            Suppliers supplier = this.saveAndReturn(supplierData);

            SupplierDatabase supplierDatabase = SupplierDatabase.builder()
                    .supplierId(supplier.getId())
                    .url(Constant.DATABASE_PREFIX + supplier.getCode())
                    .username(Constant.DATABASE_USERNAME)
                    .password(Constant.DATABASE_PASSWORD)
                    .build()
                    ;
            supplierDatabaseRepository.save(supplierDatabase);
            kafkaService.send(KafkaTopic.BROADCAST_NEW_DATABASE, supplierDatabase); // Broadcasting new supplier database is added

            Users user = new Users();
            user.setUsername(supplier.getEmail());
            user.setPassword(
                    bCryptPasswordEncoder.encode(
                            isTest ? Constant.TEST_PASSWORD : Utils.generateRandomPassword(Constant.PASSWORD_LENGTH))
            );
            user = userRepository.save(user);

            UserSupplierMap userSupplierMap = UserSupplierMap.builder()
                    .supplierId(supplier.getId())
                    .userId(user.getId())
                    .subsystem(Subsystem.CMS)
                    .build();
            userSupplierMapRepository.save(userSupplierMap);

            List<SupplierModuleData> listSupplierModuleData = supplierData.getListSupplierModule();
            if (listSupplierModuleData != null && !listSupplierModuleData.isEmpty()) {
                listSupplierModuleData.forEach(supplierModuleData -> supplierModuleData.setSupplierId(supplier.getId()));
                this.saveSupplierModules(listSupplierModuleData);
            }

            List<SupplierControlMap> listSupplierControlMap = new ArrayList<>();
            controlService.getListAllControl().forEach(control -> {
                SupplierControlMap supplierControlMap = new SupplierControlMap();
                supplierControlMap.setSupplierId(supplier.getId());
                supplierControlMap.setControlId(control.getId());
                listSupplierControlMap.add(supplierControlMap);
            });
            supplierControlMapRepository.saveAll(listSupplierControlMap);
        }
    }

    @Transactional
    public void updateSupplier(String supplierId, SupplierData supplierData) {
        supplierData.setId(supplierId);
        Suppliers supplier = this.saveAndReturn(supplierData);

        List<SupplierModuleData> listSupplierModuleData = supplierData.getListSupplierModule();
        if (listSupplierModuleData != null && !listSupplierModuleData.isEmpty()) {
            listSupplierModuleData.forEach(supplierModuleData -> supplierModuleData.setSupplierId(supplier.getId()));
            this.saveSupplierModules(listSupplierModuleData);
        }
    }


    public Suppliers saveAndReturn(SupplierData supplierData) {
        Suppliers supplier = this.modelMapper.map(supplierData, Suppliers.class);
        return supplierRepository.save(supplier);
    }

    public Page<SupplierModuleData> getListSupplierModules(String supplierId, Pageable pageable) {
        Page<SupplierModuleMap> pageSupplierModuleMap = supplierModuleMapRepository.findAllBySupplierId(supplierId, pageable);
        List<SupplierModuleData> listSupplierModuleData =
                this.modelMapper.map(pageSupplierModuleMap.getContent(), new TypeToken<List<SupplierModuleData>>() {}.getType());
        return new PageImpl<>(listSupplierModuleData, pageable, pageSupplierModuleMap.getTotalElements());
    }

    @Transactional
    public void createSupplierModules(List<SupplierModuleData> listSupplierModuleData) {
        listSupplierModuleData = listSupplierModuleData
                .stream()
                .filter(moduleData -> moduleData.getId() == null).collect(Collectors.toList());
        this.saveSupplierModules(listSupplierModuleData);
    }

    @Transactional
    public void updateSupplierModules(List<SupplierModuleData> listSupplierModuleData) {
        listSupplierModuleData = listSupplierModuleData
                .stream()
                .filter(moduleData -> moduleData.getId() != null).collect(Collectors.toList());
        this.saveSupplierModules(listSupplierModuleData);
    }

    public void saveSupplierModules(List<SupplierModuleData> listSupplierModulesData) {
        if (!listSupplierModulesData.isEmpty()) {
            List<SupplierModuleMap> listSupplierModuleMap = this.modelMapper.map(listSupplierModulesData,
                    new TypeToken<List<SupplierModuleMap>>() {}.getType());
            supplierModuleMapRepository.saveAll(listSupplierModuleMap);
        }
    }

}
