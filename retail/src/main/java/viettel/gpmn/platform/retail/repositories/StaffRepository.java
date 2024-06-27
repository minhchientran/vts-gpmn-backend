package viettel.gpmn.platform.retail.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.core.repositories.BaseRepository;
import viettel.gpmn.platform.retail.data.staff.StaffData;
import viettel.gpmn.platform.retail.data.staff.StaffQuery;
import viettel.gpmn.platform.retail.entities.Staffs;

public interface StaffRepository extends BaseRepository<Staffs> {

    @Query(value = """
        select new viettel.gpmn.platform.retail.data.staff.StaffData(
            s.id,
            s.code,
            s.name,
            p.name,
            su.name,
            s.status
        )
        from Staffs s
        left join Positions p on p.id = s.positionId
        left join SupplierUnits su on su.id = s.positionId
        where 1 = 1
            and (:#{#staffQuery.name} is not null or s.name like %:#{#staffQuery.name}% or s.code like %:#{#staffQuery.name}%)
            and (coalesce(:#{#staffQuery.positionId}, null) is not null or p.id in :#{#staffQuery.positionId})
            and (coalesce(:#{#staffQuery.unitId}, null) is not null or su.id in :#{#staffQuery.unitId})
            and (coalesce(:#{#staffQuery.status}, null) is not null or s.status in :#{#staffQuery.status})
    """)
    Page<StaffData> getListStaff(StaffQuery staffQuery, Pageable pageable);
}
