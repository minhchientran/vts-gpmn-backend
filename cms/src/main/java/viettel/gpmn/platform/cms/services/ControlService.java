package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel.gpmn.platform.cms.data.controls.ControlData;
import viettel.gpmn.platform.cms.data.controls.ControlQuery;
import viettel.gpmn.platform.cms.data.controls.SupplierControlData;
import viettel.gpmn.platform.cms.entities.Controls;
import viettel.gpmn.platform.cms.entities.SupplierControlMap;
import viettel.gpmn.platform.cms.entities.Suppliers;
import viettel.gpmn.platform.cms.repositories.ControlRepository;
import viettel.gpmn.platform.cms.repositories.SupplierControlMapRepository;
import viettel.gpmn.platform.cms.repositories.SupplierRepository;
import viettel.gpmn.platform.core.data.BaseData;
import viettel.gpmn.platform.core.services.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ControlService extends BaseService {
    
    private ControlRepository controlRepository;
    private SupplierRepository supplierRepository;
    private SupplierControlMapRepository supplierControlMapRepository;

    public Page<ControlData> getListControlsByFeatureId(String featureId, ControlQuery controlQuery, Pageable pageable) {
        Page<Controls> pageControl = controlRepository.getAllByFeatureId(featureId, controlQuery, pageable);
        List<ControlData> listControlData = this.modelMapper.map(pageControl.getContent(),
                new TypeToken<List<ControlData>>() {}.getType());
        return new PageImpl<>(listControlData, pageable, pageControl.getTotalElements());
    }

    public List<Controls> getListAllControl() {
        return controlRepository.findAll();
    }
    
    @Transactional
    public void create(List<ControlData> listData) {
        listData = listData
                .stream()
                .filter(moduleData -> moduleData.getId() == null).collect(Collectors.toList());

        List<SupplierControlMap> listSupplierControlMap = new ArrayList<>();
        List<Suppliers> listSupplier = supplierRepository.findAll();
        this.save(listData).forEach(control ->
                listSupplier.forEach(supplier -> {
                    SupplierControlMap supplierControlMap = new SupplierControlMap();
                    supplierControlMap.setSupplierId(supplier.getId());
                    supplierControlMap.setControlId(control.getId());
                    listSupplierControlMap.add(supplierControlMap);
                })
        );
        supplierControlMapRepository.saveAll(listSupplierControlMap);
    }

    @Transactional
    public void update(List<ControlData> listControlData) {
        listControlData = listControlData
                .stream()
                .filter(moduleData -> moduleData.getId() != null).collect(Collectors.toList());
        this.save(listControlData);
    }

    public List<Controls> save(List<ControlData> listControlData) {
        if (!listControlData.isEmpty()) {
            List<Controls> listControl = this.modelMapper.map(listControlData, new TypeToken<List<Controls>>() {}.getType());
            return controlRepository.saveAll(listControl);
        }
        return null;
    }

    @Transactional
    public void updateStatus(List<BaseData> listControlData) {
        listControlData.forEach(controlData ->
                controlRepository.updateStatus(controlData.getId(), controlData.getStatus()));
    }

    public Page<SupplierControlData> getListControlAttribute(String featureId, Pageable pageable) {
        return supplierControlMapRepository.getListControlAttribute(featureId, pageable);
    }
}
