package vn.viettel.cms.services;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vn.viettel.cms.data.features.FeatureData;
import vn.viettel.cms.data.features.FeatureModuleData;
import vn.viettel.cms.data.features.FeatureQuery;
import vn.viettel.cms.entities.Features;
import vn.viettel.cms.repositories.FeatureRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.viettel.core.data.InfoData;
import vn.viettel.core.services.BaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeatureService extends BaseService {

    private final FeatureRepository featuresRepository;

    public Page<FeatureData> getListFeature(FeatureQuery featureQuery, Pageable pageable) {
        Page<Features> pageFeature = featuresRepository.getListFeature(featureQuery, pageable);
        List<FeatureData> listFeatureData = modelMapper.map(pageFeature.getContent(),
                new TypeToken<List<FeatureData>>() {}.getType());
        return new PageImpl<>(listFeatureData, pageable, pageFeature.getTotalElements());
    }

    public List<FeatureData> getListParentFeature() {
        List<Features> pageFeature = featuresRepository.getListParentFeature();
        return modelMapper.map(pageFeature, new TypeToken<List<FeatureData>>() {}.getType());
    }

    @Transactional
    public void createFeatures(List<FeatureData> listFeatureData) {
        List<Features> listFeature = modelMapper.map(listFeatureData, new TypeToken<List<Features>>() {}.getType());
        listFeature.forEach(feature -> feature.setId(null));
        featuresRepository.saveAll(listFeature);
    }

    @Transactional
    public void updateFeatures(List<FeatureData> listFeatureData) {
        listFeatureData = listFeatureData
                .stream()
                .filter(featureData -> featureData.getId() != null).collect(Collectors.toList());
        if (!listFeatureData.isEmpty()) {
            List<Features> listFeature = modelMapper.map(listFeatureData, new TypeToken<List<Features>>() {}.getType());
            featuresRepository.saveAll(listFeature);
        }
    }

    public Page<FeatureModuleData> getFeaturesModule(String moduleId, Boolean isInModule, Pageable pageable) {
        return featuresRepository.getFeatureInModule(moduleId, isInModule, pageable);
    }

}
