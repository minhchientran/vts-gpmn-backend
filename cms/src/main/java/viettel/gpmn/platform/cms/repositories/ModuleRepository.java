package viettel.gpmn.platform.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.cms.data.modules.ModuleQuery;
import viettel.gpmn.platform.cms.entities.Modules;
import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.repositories.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository extends BaseRepository<Modules> {

    @Query(value = """
        select m
        from Modules m
        left join SupplierModuleMap smm on smm.moduleId = m.id
        where 1 = 1
            and smm.supplierId not like :supplierId
    """)
    List<Modules> getListModuleNotInSupplierId(String supplierId);

    Optional<Modules> findByIdAndStatus(String id, DBStatus status);

    @Query(value = """
                select m
                from Modules m
                where 1 = 1
                    and ( :#{#moduleQuery.name} is null
                        or m.name like %:#{#moduleQuery.name}%
                        or m.code like %:#{#moduleQuery.name}%
                        )
                    and ( coalesce(:#{#moduleQuery.subsystem}, null) is null or m.subsystem in :#{#moduleQuery.subsystem} )
                    and ( coalesce(:#{#moduleQuery.status}, null)  is null or m.status in :#{#moduleQuery.status} )
            """)
    Page<Modules> getListModule(ModuleQuery moduleQuery, Pageable pageable);
}
