package viettel.gpmn.platform.cms.services;

import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import viettel.gpmn.platform.cms.data.suppliers.SupplierData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierListData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierModuleData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierQuery;
import viettel.gpmn.platform.cms.entities.SupplierModuleMap;
import viettel.gpmn.platform.cms.entities.Suppliers;
import viettel.gpmn.platform.cms.repositories.SupplierDatabaseRepository;
import viettel.gpmn.platform.cms.repositories.SupplierModuleMapRepository;
import viettel.gpmn.platform.cms.repositories.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel.gpmn.platform.core.services.GenericSaveService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupplierService extends GenericSaveService<Suppliers, SupplierData, SupplierRepository> {

    private SupplierDatabaseRepository supplierDatabaseRepository;
    private SupplierModuleMapRepository supplierModuleMapRepository;

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
                .filter(moduleData -> moduleData.getId() != null).collect(Collectors.toList());
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
