package vn.viettel.cms.services;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vn.viettel.cms.data.features.FeatureAddControlData;
import vn.viettel.cms.data.features.FeatureData;
import vn.viettel.cms.data.features.FeatureQuery;
import vn.viettel.cms.entities.FeatureControlMap;
import vn.viettel.cms.entities.Features;
import vn.viettel.cms.repositories.FeatureControlMapRepository;
import vn.viettel.cms.repositories.FeatureRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.viettel.core.services.BaseService;

import java.util.List;

@Service
@AllArgsConstructor
public class FeatureService extends BaseService {

    private final FeatureRepository featuresRepository;
    private final FeatureControlMapRepository featureControlMapRepository;

    @Transactional
    public void createFeature(FeatureData featureData) {
        Features feature = modelMapper.map(featureData, Features.class);
        featuresRepository.save(feature);
    }

    public Page<FeatureData> getListFeature(FeatureQuery featureQuery, Pageable pageable) {
        Page<Features> pageFeature = featuresRepository.getListFeature(featureQuery, pageable);
        List<FeatureData> listFeatureData = modelMapper.map(pageFeature.getContent(),
                new TypeToken<List<FeatureData>>() {}.getType());
        return new PageImpl<>(listFeatureData, pageable, pageFeature.getTotalElements());
    }

    @Transactional
    public void addControls2Feature(List<FeatureAddControlData> listFeatureAddControlData) {
        List<FeatureControlMap> listFeatureControlMap = modelMapper.map(listFeatureAddControlData,
                new TypeToken<List<FeatureControlMap>>() {}.getType());

        featureControlMapRepository.saveAll(listFeatureControlMap);
    }
}
