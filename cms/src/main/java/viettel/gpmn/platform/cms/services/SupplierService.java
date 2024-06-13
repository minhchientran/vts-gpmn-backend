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
import viettel.gpmn.platform.core.enums.Subsystem;
import viettel.gpmn.platform.core.services.GenericSaveService;
import viettel.gpmn.platform.core.utilities.Constant;
import viettel.gpmn.platform.core.utilities.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupplierService extends GenericSaveService<Suppliers, SupplierData, SupplierRepository> {

    private Boolean isTest;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SupplierDatabaseRepository supplierDatabaseRepository;
    private SupplierModuleMapRepository supplierModuleMapRepository;
    private UserRepository userRepository;
    private UserSupplierMapRepository userSupplierMapRepository;

    public Page<SupplierListData> getListSuppliers(SupplierQuery supplierQuery, Pageable pageable) {
        Page<Suppliers> pageSupplier = this.repository.getListSupplier(supplierQuery, pageable);
        List<SupplierListData> listSupplierListData = this.modelMapper.map(pageSupplier.getContent(),
                new TypeToken<List<SupplierListData>>() {}.getType());
        return new PageImpl<>(listSupplierListData, pageable, pageSupplier.getTotalElements());
    }

    public SupplierData getSupplierDetail(String supplierId) {
        Suppliers suppliers = this.repository.findById(supplierId).orElseThrow();
        // TODO image
        return this.modelMapper.map(suppliers, SupplierData.class);
    }

    @Transactional
    @Override
    public void create(List<SupplierData> listSupplierData) {
        listSupplierData = listSupplierData
                .stream()
                .filter(moduleData -> moduleData.getId() == null).collect(Collectors.toList());
        List<Suppliers> listSupplier = this.saveAndReturn(listSupplierData);

        if (listSupplier != null && !listSupplier.isEmpty()) {
            listSupplier.forEach(supplier -> {
                SupplierDatabase supplierDatabase = SupplierDatabase.builder()
                        .supplierId(supplier.getId())
                        .url(Constant.DATABASE_PREFIX + supplier.getCode())
                        .username(Constant.DATABASE_USERNAME)
                        .password(Constant.DATABASE_PASSWORD)
                        .build()
                        ;
                supplierDatabaseRepository.save(supplierDatabase);


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
            });
        }
    }

    public List<Suppliers> saveAndReturn(List<SupplierData> listSupplierData) {
        if (!listSupplierData.isEmpty()) {
            List<Suppliers> listSuppliers = this.modelMapper.map(listSupplierData, new TypeToken<List<Suppliers>>() {}.getType());
            return this.repository.saveAll(listSuppliers);
        }
        return null;
    }

    @Override
    public void save(List<SupplierData> listSupplierData) {
        if (!listSupplierData.isEmpty()) {
            List<Suppliers> listSuppliers = this.modelMapper.map(listSupplierData, new TypeToken<List<Suppliers>>() {}.getType());
            this.repository.saveAll(listSuppliers);
        }
    }

    public List<SupplierModuleData> getListSupplierModules(String supplierId) {
        List<SupplierModuleMap> listSupplierModuleMap = supplierModuleMapRepository.findAllBySupplierId(supplierId);
        return this.modelMapper.map(listSupplierModuleMap, new TypeToken<List<SupplierModuleData>>() {}.getType());
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
