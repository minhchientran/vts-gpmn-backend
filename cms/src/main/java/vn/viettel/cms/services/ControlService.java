package vn.viettel.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.viettel.cms.data.controls.ControlData;
import vn.viettel.cms.data.features.FeatureData;
import vn.viettel.cms.entities.Controls;
import vn.viettel.cms.repositories.ControlRepository;
import vn.viettel.core.services.BaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ControlService extends BaseService {

    private final ControlRepository controlRepository;

    public Page<ControlData> getListControlByFeatureId(String featureId, Pageable pageable) {
        Page<Controls> pageControl = controlRepository.getAllByFeatureId(featureId, pageable);
        List<ControlData> listControlData = modelMapper.map(pageControl.getContent(),
                new TypeToken<List<ControlData>>() {}.getType());
        return new PageImpl<>(listControlData, pageable, pageControl.getTotalElements());
    }
    @Transactional
    public void createControls(List<ControlData> listControlData) {
        listControlData = listControlData.stream().filter(controlData -> controlData.getId() == null).collect(Collectors.toList());
        this.saveControls(listControlData);
    }

    @Transactional
    public void updateControls(List<ControlData> listControlData) {
        listControlData = listControlData.stream().filter(controlData -> controlData.getId() != null).collect(Collectors.toList());
        this.saveControls(listControlData);
    }

    public void saveControls(List<ControlData> listControlData) {
        if (!listControlData.isEmpty()) {
            List<Controls> listControl = modelMapper.map(listControlData, new TypeToken<List<Controls>>() {}.getType());
            controlRepository.saveAll(listControl);
        }
    }
}
