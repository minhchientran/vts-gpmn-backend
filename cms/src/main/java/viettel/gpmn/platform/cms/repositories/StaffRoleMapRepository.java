package viettel.gpmn.platform.cms.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.cms.entities.StaffRoleMap;
import viettel.gpmn.platform.core.repositories.BaseRepository;

public interface StaffRoleMapRepository extends BaseRepository<StaffRoleMap> {
    @Modifying
    @Query(value = "update StaffRoleMap srm set srm.fromDate = :#{#staffRoleMap.fromDate}, srm.toDate =:#{#staffRoleMap.toDate} where srm.id = :staffRoleId")
    void updateStaffRoleMap(String staffRoleId, StaffRoleMap staffRoleMap);
}
