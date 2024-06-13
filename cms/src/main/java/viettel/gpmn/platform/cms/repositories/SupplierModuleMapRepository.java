package viettel.gpmn.platform.cms.repositories;

import viettel.gpmn.platform.cms.entities.SupplierModuleMap;
import viettel.gpmn.platform.core.repositories.BaseRepository;

import java.util.List;

public interface SupplierModuleMapRepository extends BaseRepository<SupplierModuleMap> {
    List<SupplierModuleMap> findAllBySupplierId(String supplierId);
}
