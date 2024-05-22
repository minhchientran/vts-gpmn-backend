package vn.viettel.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import vn.viettel.cms.data.modules.ModuleQuery;
import vn.viettel.cms.entities.Modules;
import vn.viettel.core.repositories.BaseRepository;

public interface ModuleRepository extends BaseRepository<Modules> {
    @Query(value = """
                select m
                from Modules m
                where 1 = 1
                    and ( :#{#moduleQuery.name} is null
                        or m.name like concat('%', :#{#moduleQuery.name}, '%')
                        or m.code like concat('%', :#{#moduleQuery.name}, '%')
                        )
                    and ( :#{#moduleQuery.description} is null or m.description like %:#{#moduleQuery.description}% )
                    and ( :#{#moduleQuery.subsystem} is null or m.subsystem = :#{#moduleQuery.subsystem} )
                    and ( :#{#moduleQuery.status} is null or m.status = :#{#moduleQuery.status} )
            """)
    Page<Modules> getListModule(ModuleQuery moduleQuery, Pageable pageable);
}
