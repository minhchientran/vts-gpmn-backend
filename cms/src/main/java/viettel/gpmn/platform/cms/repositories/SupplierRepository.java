package viettel.gpmn.platform.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
}
