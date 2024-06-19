package viettel.gpmn.platform.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import viettel.gpmn.platform.cms.entities.SupplierModuleMap;
import viettel.gpmn.platform.core.repositories.BaseRepository;

public interface SupplierModuleMapRepository extends BaseRepository<SupplierModuleMap> {
    Page<SupplierModuleMap> findAllBySupplierId(String supplierId, Pageable pageable);
}
