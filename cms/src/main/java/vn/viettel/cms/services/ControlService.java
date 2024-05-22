package vn.viettel.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.viettel.cms.data.controls.ControlData;
import vn.viettel.cms.data.features.FeatureAddControlData;
import vn.viettel.cms.data.features.FeatureData;
import vn.viettel.cms.data.features.FeatureQuery;
import vn.viettel.cms.entities.Controls;
import vn.viettel.cms.entities.FeatureControlMap;
import vn.viettel.cms.entities.Features;
import vn.viettel.cms.repositories.ControlRepository;
import vn.viettel.cms.repositories.FeatureControlMapRepository;
import vn.viettel.cms.repositories.FeatureRepository;
import vn.viettel.core.services.BaseService;

import java.util.List;

@Service
@AllArgsConstructor
public class ControlService extends BaseService {

    private final ControlRepository controlRepository;
    private final FeatureControlMapRepository featureControlMapRepository;

    public Page<ControlData> getListControlByFeatureId(String featureId, Pageable pageable) {
        Page<Controls> pageControl = controlRepository.getListControlByFeatureId(featureId, pageable);
        List<ControlData> listControlData = modelMapper.map(pageControl.getContent(),
                new TypeToken<List<FeatureData>>() {}.getType());
        return new PageImpl<>(listControlData, pageable, pageControl.getTotalElements());
    }
    @Transactional
    public void createControls(String featureId, ControlData controlData) {
        Controls control = modelMapper.map(controlData, Controls.class);
        control = controlRepository.save(control);
        featureControlMapRepository.save(
                new FeatureControlMap(featureId, control.getId())
        );
    }

}
