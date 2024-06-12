package viettel.gpmn.platform.cms.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.cms.data.modules.ModuleAddFeatureData;
import viettel.gpmn.platform.cms.entities.ModuleFeatureMap;
import viettel.gpmn.platform.core.repositories.BaseRepository;

public interface ModuleFeatureMapRepository extends BaseRepository<ModuleFeatureMap> {
    @Modifying
    @Query(value = """
        update ModuleFeatureMap
        set status = :#{#moduleAddFeatureData.status}
        where moduleId = :#{#moduleAddFeatureData.moduleId} and featureId = :#{#moduleAddFeatureData.featureId}
    """)
    void updateStatusByModuleIdAndFeatureId(ModuleAddFeatureData moduleAddFeatureData);
}
