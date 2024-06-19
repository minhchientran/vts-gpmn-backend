package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel.gpmn.platform.cms.data.modules.ModuleAddFeatureData;
import viettel.gpmn.platform.cms.data.modules.ModuleQuery;
import viettel.gpmn.platform.cms.repositories.ModuleFeatureMapRepository;
import viettel.gpmn.platform.cms.data.modules.ModuleData;
import viettel.gpmn.platform.cms.entities.ModuleFeatureMap;
import viettel.gpmn.platform.cms.entities.Modules;
import viettel.gpmn.platform.cms.repositories.ModuleRepository;
import viettel.gpmn.platform.core.configs.tenant.TenantContext;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.services.GenericSaveService;

import java.util.List;

@Service
@AllArgsConstructor
public class ModuleService extends GenericSaveService<Modules, ModuleData, ModuleRepository> {

    private ModuleFeatureMapRepository moduleFeatureMapRepository;

    public Page<ModuleData> getListModules(ModuleQuery moduleQuery, Pageable pageable) {
        Page<Modules> pageModule = this.repository.getListModule(moduleQuery, pageable);
        List<ModuleData> listModuleData = modelMapper.map(pageModule.getContent(),
                new TypeToken<List<ModuleData>>() {}.getType());
        return new PageImpl<>(listModuleData, pageable, pageModule.getTotalElements());
    }

    public List<InfoData> getListAllModules() {
        List<Modules> listAllModules = this.repository.getListModuleNotInSupplierId(TenantContext.getSupplierId());
        return modelMapper.map(listAllModules, new TypeToken<List<InfoData>>() {}.getType());
    }

    public ModuleData getModuleDetail(String moduleId) {
        Modules module = this.repository.findByIdAndStatus(moduleId, DBStatus.ACTIVE)
                .orElseThrow();
        return this.modelMapper.map(module, ModuleData.class);
    }

    @Override
    public List<Modules> save(List<ModuleData> listModuleData) {
        if (!listModuleData.isEmpty()) {
            List<Modules> listModule = modelMapper.map(listModuleData, new TypeToken<List<Modules>>() {}.getType());
            return this.repository.saveAll(listModule);
        }
        return null;
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
