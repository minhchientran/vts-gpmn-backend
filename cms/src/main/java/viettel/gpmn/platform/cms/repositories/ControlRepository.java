package viettel.gpmn.platform.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.cms.data.controls.ControlQuery;
import viettel.gpmn.platform.cms.entities.Controls;
import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.repositories.BaseRepository;

public interface ControlRepository extends BaseRepository<Controls> {

    @Query(value = """
        select c
        from Controls c
        where 1 = 1
            and c.featureId = :featureId
            and (:#{#controlQuery.name} is null or c.name like %:#{#controlQuery.name}% or c.code like %:#{#controlQuery.name}%)
            and (coalesce(:#{#controlQuery.status}, null) is null or c.status in :#{#controlQuery.status})
    """)
    Page<Controls> getAllByFeatureId(String featureId, ControlQuery controlQuery, Pageable pageable);

    @Modifying
    @Query("update Controls c set c.status = :status where c.id = :controlId")
    void updateStatus(String controlId, DBStatus status);
}
