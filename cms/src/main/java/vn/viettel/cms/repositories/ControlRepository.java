package vn.viettel.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import vn.viettel.cms.entities.Controls;
import vn.viettel.core.repositories.BaseRepository;

public interface ControlRepository extends BaseRepository<Controls> {
    @Query(value = """
        select c
        from Controls c
        join FeatureControlMap fcm
            on fcm.controlId = c.id
            and fcm.status = vn.viettel.core.enums.DBStatus.ACTIVE
        where 1 = 1
            and fcm.featureId = :featureId
    """)
    Page<Controls> getListControlByFeatureId(String featureId, Pageable pageable);
}
