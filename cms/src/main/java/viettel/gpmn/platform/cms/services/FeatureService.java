package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel.gpmn.platform.cms.data.features.FeatureData;
import viettel.gpmn.platform.cms.data.features.FeatureModuleData;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.entities.Features;
import viettel.gpmn.platform.cms.repositories.FeatureRepository;
import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.services.BaseService;

import java.util.List;

@Service
@AllArgsConstructor
public class FeatureService extends BaseService {
    
    private FeatureRepository featureRepository;

    public Page<FeatureData> getListFeatures(FeatureQuery featureQuery, Pageable pageable) {
        return featureRepository.getListFeature(featureQuery, pageable);
    }

    public List<FeatureData> getListParentFeature() {
        List<Features> pageFeature = featureRepository.getAllByStatus(DBStatus.ACTIVE);
        return this.modelMapper.map(pageFeature, new TypeToken<List<FeatureData>>() {}.getType());
    }

    public FeatureData getFeatureDetail(String featureId) {
        Features feature = featureRepository.findByIdAndStatus(featureId, DBStatus.ACTIVE)
                .orElseThrow();
        return this.modelMapper.map(feature, FeatureData.class);
    }

    @Transactional
    public void save(List<FeatureData> listFeatureData) {
        if (!listFeatureData.isEmpty()) {
            List<Features> listFeature = this.modelMapper.map(listFeatureData, new TypeToken<List<Features>>() {}.getType());
            featureRepository.saveAll(listFeature);
        }
    }

    public Page<FeatureModuleData> getFeaturesModule(String moduleId, Boolean isInModule, FeatureQuery featureQuery, Pageable pageable) {
        return featureRepository.getFeatureInModule(moduleId, isInModule, featureQuery, pageable);
    }

    public Page<FeatureModuleData> getListFeatureByRole(String roleId, FeatureQuery featureQuery, Pageable pageable) {
        return featureRepository.getListFeatureByRole(roleId, featureQuery, pageable);
    }
    
    public List<FeatureData> getListFeatureHierarchy(String roleId) {
        List<Features> listFeature = featureRepository.getListFeatureHierarchy(roleId);
        return this.modelMapper.map(listFeature, new TypeToken<List<FeatureData>>() {}.getType());
    }

}
