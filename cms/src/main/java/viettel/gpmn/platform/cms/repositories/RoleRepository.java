package viettel.gpmn.platform.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.cms.data.role.RoleData;
import viettel.gpmn.platform.cms.data.role.RoleQuery;
import viettel.gpmn.platform.cms.entities.Roles;
import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.repositories.BaseRepository;

import java.util.List;

public interface RoleRepository extends BaseRepository<Roles> {

    @Query(value = """
        select r
        from Roles r
        where 1 = 1
            and (
                :#{#roleQuery.name} is null
                or r.name like %:#{#roleQuery.name}%
                or r.code like %:#{#roleQuery.name}%
            )
            and (coalesce(:#{#roleQuery.status}, null) is null or r.status in :#{#roleQuery.status})
    """)
    Page<Roles> getListRole(RoleQuery roleQuery, Pageable pageable);

    @Query(value = """
        select new viettel.gpmn.platform.cms.data.role.RoleData(
            srm.id,
            r.code,
            r.name,
            srm.status,
            srm.fromDate,
            srm.toDate
        )
        from Roles r
        join StaffRoleMap srm on srm.roleId = r.id
        where 1 = 1
            and srm.staffId = :staffId
            and (
                :#{#roleQuery.name} is null
                or r.name like %:#{#roleQuery.name}%
                or r.code like %:#{#roleQuery.name}%
            )
            and (coalesce(:#{#roleQuery.status}, null) is null or r.status in :#{#roleQuery.status})
    """)
    Page<RoleData> getListRoleByStaffId(String staffId, RoleQuery roleQuery, Pageable pageable);

    @Query(value = """
        select r
        from Roles r
        left join StaffRoleMap srm on srm.roleId = r.id and srm.staffId = :staffId
        where 1 = 1
            and srm.staffId is null
    """)
    List<Roles> getListRoleExcludeStaffId(String staffId);

    @Modifying
    @Query(value = "update Roles r set r.status = :status where r.id = :roleId")
    void updateStatus(String roleId, DBStatus status);

    @Modifying
    @Query(value = "update StaffRoleMap srm set srm.status = :status where srm.id = :staffRoleId")
    void updateStaffRoleStatus(String staffRoleId, DBStatus status);
}
