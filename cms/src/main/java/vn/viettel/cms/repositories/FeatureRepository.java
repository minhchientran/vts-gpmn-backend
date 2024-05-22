package vn.viettel.cms.repositories;

import vn.viettel.cms.data.features.FeatureData;
import vn.viettel.cms.data.features.FeatureQuery;
import vn.viettel.cms.entities.Features;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import vn.viettel.core.data.users.UserFeatureData;
import vn.viettel.core.repositories.BaseRepository;

import java.util.List;

public interface FeatureRepository extends BaseRepository<Features> {

    @Query(value = """
               select new vn.viettel.core.data.users.UserFeatureData (
                   f.id,
                   f.code,
                   c.id,
                   c.code
                )
                from Features f
                left join FeatureControlMap fcm on fcm.featureId = f.id and fcm.status = vn.viettel.core.enums.DBStatus.ACTIVE
                left join Controls c on c.id = fcm.controlId and c.status = vn.viettel.core.enums.DBStatus.ACTIVE
                join ModuleFeatureMap mfm on mfm.featureId = f.id and mfm.status = vn.viettel.core.enums.DBStatus.ACTIVE
                join Modules m on m.id = mfm.moduleId and m.status = vn.viettel.core.enums.DBStatus.ACTIVE
                join SupplierModuleMap smm on smm.moduleId = m.id and smm.status = vn.viettel.core.enums.DBStatus.ACTIVE
                join Suppliers s on s.id = smm.supplierId and s.status = vn.viettel.core.enums.DBStatus.ACTIVE
                join UserSupplierMap usm on usm.supplierId = s.id and usm.status = vn.viettel.core.enums.DBStatus.ACTIVE
                join Users u on u.id = usm.userId and u.status = vn.viettel.core.enums.DBStatus.ACTIVE
                where 1 = 1
                   and u.id = :userId
               and s.id = :supplierId
            """)
    List<UserFeatureData> getAdminFeatures(String userId, String supplierId);

    @Query(value = """
                select f
                from Features f
                where 1 = 1
                    and ( :#{#featureQuery.name} is null
                        or f.name like concat('%', :#{#featureQuery.name}, '%')
                        or f.code like concat('%', :#{#featureQuery.name}, '%')
                        )
                    and ( :#{#featureQuery.description} is null or f.description like %:#{#featureQuery.description}% )
                    and ( :#{#featureQuery.parentId} is null or f.parentFeatureId = :#{#featureQuery.description} )
                    and ( :#{#featureQuery.type} is null or f.type = :#{#featureQuery.type} )
                    and ( :#{#featureQuery.status} is null or f.status = :#{#featureQuery.status} )
            """)
    Page<Features> getListFeature(FeatureQuery featureQuery, Pageable pageable);
}
