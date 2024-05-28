package vn.viettel.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.viettel.cms.data.features.FeatureData;
import vn.viettel.cms.data.modules.ModuleAddFeatureData;
import vn.viettel.cms.data.modules.ModuleData;
import vn.viettel.cms.data.modules.ModuleQuery;
import vn.viettel.cms.entities.ModuleFeatureMap;
import vn.viettel.cms.entities.Modules;
import vn.viettel.cms.repositories.ModuleFeatureMapRepository;
import vn.viettel.cms.repositories.ModuleRepository;
import vn.viettel.core.services.BaseService;

import java.util.List;
import java.util.stream.Collectors;

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
    public void createModules(List<ModuleData> listModuleData) {
        List<Modules> listModule = modelMapper.map(listModuleData, new TypeToken<List<Modules>>() {}.getType());
        listModule.forEach(module -> module.setId(null));
        moduleRepository.saveAll(listModule);
    }

    @Transactional
    public void updateModules(List<ModuleData> listModuleData) {
        listModuleData = listModuleData
                .stream()
                .filter(moduleData -> moduleData.getId() != null).collect(Collectors.toList());
        if (!listModuleData.isEmpty()) {
            List<Modules> listModule = modelMapper.map(listModuleData, new TypeToken<List<Modules>>() {}.getType());
            moduleRepository.saveAll(listModule);
        }
    }

    @Transactional
    public void addFeatures2Module(List<ModuleAddFeatureData> listModuleAddFeatureData) {
        List<ModuleFeatureMap> listModuleFeatureMap = modelMapper.map(listModuleAddFeatureData,
                new TypeToken<List<ModuleFeatureMap>>() {}.getType());

        moduleFeatureMapRepository.saveAll(listModuleFeatureMap);
    }

    @Transactional
    public void updateFeatureInModule(List<ModuleAddFeatureData> listModuleAddFeatureData) {
        listModuleAddFeatureData.forEach(moduleFeatureMapRepository::updateStatusByModuleIdAndFeatureId);
    }
}
