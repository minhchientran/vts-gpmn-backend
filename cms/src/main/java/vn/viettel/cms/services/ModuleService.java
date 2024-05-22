package vn.viettel.cms.services;

import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vn.viettel.cms.data.features.FeatureAddControlData;
import vn.viettel.cms.data.features.FeatureData;
import vn.viettel.cms.data.features.FeatureQuery;
import vn.viettel.cms.data.modules.ModuleAddFeatureData;
import vn.viettel.cms.data.modules.ModuleData;
import vn.viettel.cms.data.modules.ModuleQuery;
import vn.viettel.cms.entities.FeatureControlMap;
import vn.viettel.cms.entities.Features;
import vn.viettel.cms.entities.ModuleFeatureMap;
import vn.viettel.cms.entities.Modules;
import vn.viettel.cms.repositories.ModuleFeatureMapRepository;
import vn.viettel.cms.repositories.ModuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.viettel.core.services.BaseService;

import java.util.List;

@Service
@AllArgsConstructor
public class ModuleService extends BaseService {

    private final ModuleRepository moduleRepository;
    private final ModuleFeatureMapRepository moduleFeatureMapRepository;

    public Page<ModuleData> getListModule(ModuleQuery moduleQuery, Pageable pageable) {
        Page<Modules> pageModule = moduleRepository.getListModule(moduleQuery, pageable);
        List<ModuleData> listModuleData = modelMapper.map(pageModule.getContent(),
                new TypeToken<List<FeatureData>>() {}.getType());
        return new PageImpl<>(listModuleData, pageable, pageModule.getTotalElements());
    }

    @Transactional
    public void createModule(ModuleData moduleData) {
        Modules module = modelMapper.map(moduleData, Modules.class);
        moduleRepository.save(module);
    }

    @Transactional
    public void addFeatures2Module(List<ModuleAddFeatureData> listModuleAddFeatureData) {
        List<ModuleFeatureMap> listModuleFeatureMap = modelMapper.map(listModuleAddFeatureData,
                new TypeToken<List<FeatureControlMap>>() {}.getType());

        moduleFeatureMapRepository.saveAll(listModuleFeatureMap);
    }
}
