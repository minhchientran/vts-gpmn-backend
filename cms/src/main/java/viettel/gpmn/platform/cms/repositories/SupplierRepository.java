package viettel.gpmn.platform.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.cms.data.suppliers.SupplierForUserData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierQuery;
import viettel.gpmn.platform.cms.entities.Suppliers;
import viettel.gpmn.platform.core.repositories.BaseRepository;

public interface SupplierRepository extends BaseRepository<Suppliers> {

    @Query(value = """
        select s
        from Suppliers s
        where 1 = 1
            and (:#{#supplierQuery.name} is null
                or s.name like %:#{#supplierQuery.name}%
                or s.companyCode like %:#{#supplierQuery.name}%
            )
            and (:#{#supplierQuery.code} is null or s.code like %:#{#supplierQuery.code}%)
            and (:#{#supplierQuery.status} is null or s.status = :#{#supplierQuery.status})
    """)
    Page<Suppliers> getListSupplier(SupplierQuery supplierQuery, Pageable pageable);

    @Query(value = """
        select new viettel.gpmn.platform.cms.data.suppliers.SupplierForUserData(
            s.id,
            s.name,
            s.address,
            s.logo,
            s.rating,
            s.status
        )
        from Suppliers s
        left join UserSupplierMap usm on usm.supplierId = s.id and usm.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
        where 1 = 1
            and usm.userId = :userId
            and (:name is null or s.name like %:name%)
            and 1 = (
                case
                when (:isRegistered = true and usm.id is not null) then 1
                when (:isRegistered = false and usm.id is null) then 1
                else 0
                end
           )
    """)
    Page<SupplierForUserData> getListSupplierForUser(String userId, String name, Boolean isRegistered, Pageable pageable);
}
