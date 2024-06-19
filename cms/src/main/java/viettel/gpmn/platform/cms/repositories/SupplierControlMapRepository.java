package viettel.gpmn.platform.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.cms.data.controls.SupplierControlData;
import viettel.gpmn.platform.cms.entities.SupplierControlMap;
import viettel.gpmn.platform.core.repositories.BaseRepository;

public interface SupplierControlMapRepository extends BaseRepository<SupplierControlMap> {
    @Query(value = """
        select new viettel.gpmn.platform.cms.data.controls.SupplierControlData(
            scm.id,
            c.code,
            c.name,
            scm.supplierId,
            scm.controlId,
            c.controlType,
            scm.controlAttribute
        )
        from SupplierControlMap scm
        join Controls c on c.id = scm.controlId
        where 1 = 1
            and c.featureId = :featureId
    """)
    Page<SupplierControlData> getListControlAttribute(String featureId, Pageable pageable);
}
