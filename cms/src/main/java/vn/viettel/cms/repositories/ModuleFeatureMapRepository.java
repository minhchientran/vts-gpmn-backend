package vn.viettel.cms.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.viettel.cms.data.modules.ModuleAddFeatureData;
import vn.viettel.cms.entities.ModuleFeatureMap;
import vn.viettel.core.repositories.BaseRepository;

public interface ModuleFeatureMapRepository extends BaseRepository<ModuleFeatureMap> {
    @Modifying
    @Query(value = """
        update ModuleFeatureMap
        set status = :#{#moduleAddFeatureData.status}
        where moduleId = :#{#moduleAddFeatureData.moduleId} and featureId = :#{#moduleAddFeatureData.featureId}
    """)
    void updateStatusByModuleIdAndFeatureId(ModuleAddFeatureData moduleAddFeatureData);
}
