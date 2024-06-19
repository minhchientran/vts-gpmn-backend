package viettel.gpmn.platform.retail.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.repositories.BaseRepository;
import viettel.gpmn.platform.retail.data.staff.StaffRoleData;

public interface StaffRoleMapRepository extends BaseRepository<StaffRoleMap> {

    @Query(value = """
        select new viettel.gpmn.platform.retail.data.staff.StaffRoleData(
            srm.id,
            r.code,
            r.name,
            srm.staffId,
            srm.roleId,
            srm.fromDate,
            srm.toDate,
            srm.status
        )
        from StaffRoleMap srm
        join Roles r on srm.roleId = r.id
        where 1 = 1
            and srm.staffId = :staffId
    """)
    Page<StaffRoleData> getListStaffRole(String staffId, Pageable pageable);

    @Modifying
    @Query(value = "update StaffRoleMap srm set srm.status = :status where srm.id = :id")
    void updateStaffRoleStatus(String id, DBStatus status);
}
