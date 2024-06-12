package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import viettel.gpmn.platform.cms.data.controls.ControlData;
import viettel.gpmn.platform.cms.repositories.ControlRepository;
import viettel.gpmn.platform.cms.data.controls.ControlQuery;
import viettel.gpmn.platform.cms.entities.Controls;
import viettel.gpmn.platform.core.services.GenericSaveService;

import java.util.List;

@Service
@AllArgsConstructor
public class ControlService extends GenericSaveService<Controls, ControlData, ControlRepository> {

    public Page<ControlData> getListControlsByFeatureId(String featureId, ControlQuery controlQuery, Pageable pageable) {
        Page<Controls> pageControl = repository.getAllByFeatureId(featureId, controlQuery, pageable);
        List<ControlData> listControlData = modelMapper.map(pageControl.getContent(),
                new TypeToken<List<ControlData>>() {}.getType());
        return new PageImpl<>(listControlData, pageable, pageControl.getTotalElements());
    }

    @Override
    public void save(List<ControlData> listControlData) {
        if (!listControlData.isEmpty()) {
            List<Controls> listControl = modelMapper.map(listControlData, new TypeToken<List<Controls>>() {}.getType());
            repository.saveAll(listControl);
        }
    }

}
