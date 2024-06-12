package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import viettel.gpmn.platform.cms.data.features.FeatureModuleData;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.repositories.FeatureRepository;
import viettel.gpmn.platform.cms.data.features.FeatureData;
import viettel.gpmn.platform.cms.entities.Features;
import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.services.GenericSaveService;

import java.util.List;

@Service
@AllArgsConstructor
public class FeatureService extends GenericSaveService<Features, FeatureData, FeatureRepository> {

    public Page<FeatureData> getListFeatures(FeatureQuery featureQuery, Pageable pageable) {
        return this.repository.getListFeature(featureQuery, pageable);
    }

    public List<FeatureData> getListParentFeature() {
        List<Features> pageFeature = this.repository.getAllByStatus(DBStatus.ACTIVE);
        return this.modelMapper.map(pageFeature, new TypeToken<List<FeatureData>>() {}.getType());
    }

    public FeatureData getFeatureDetail(String featureId) {
        Features feature = this.repository.findByIdAndStatus(featureId, DBStatus.ACTIVE)
                .orElseThrow();
        return this.modelMapper.map(feature, FeatureData.class);
    }

    @Override
    public void save(List<FeatureData> listFeatureData) {
        if (!listFeatureData.isEmpty()) {
            List<Features> listFeature = this.modelMapper.map(listFeatureData, new TypeToken<List<Features>>() {}.getType());
            this.repository.saveAll(listFeature);
        }
    }

    public Page<FeatureModuleData> getFeaturesModule(String moduleId, Boolean isInModule, FeatureQuery featureQuery, Pageable pageable) {
        return this.repository.getFeatureInModule(moduleId, isInModule, featureQuery, pageable);
    }

}
