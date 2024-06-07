package vn.viettel.cms.repositories;

import vn.viettel.cms.data.features.FeatureModuleData;
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
                left join Controls c on c.featureId = f.id and c.status = vn.viettel.core.enums.DBStatus.ACTIVE
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
                left join Features pf on pf.id = f.parentFeatureId and pf.status = vn.viettel.core.enums.DBStatus.ACTIVE
                where 1 = 1
                    and ( :#{#featureQuery.name} is null
                        or f.name like %:#{#featureQuery.name}%
                        or f.code like %:#{#featureQuery.name}%
                        )
                    and ( :#{#featureQuery.description} is null or f.description like %:#{#featureQuery.description}% )
                    and ( :#{#featureQuery.parentId} is null or f.parentFeatureId = :#{#featureQuery.description} )
                    and ( :#{#featureQuery.featureType} is null or f.featureType = :#{#featureQuery.featureType} )
                    and ( :#{#featureQuery.status} is null or f.status = :#{#featureQuery.status} )
            """)
    Page<Features> getListFeature(FeatureQuery featureQuery, Pageable pageable);

    @Query(value = """
                select f
                from Features f
                join Features cf on f.id = cf.parentFeatureId
                where 1 = 1
                    and f.status = vn.viettel.core.enums.DBStatus.ACTIVE
                    and cf.status = vn.viettel.core.enums.DBStatus.ACTIVE
            """)
    List<Features> getListParentFeature();
    @Query(value = """
                select distinct new vn.viettel.cms.data.features.FeatureModuleData(
                    f.code,
                    f.name,
                    pf.code,
                    f.featureType,
                    f.url,
                    f.seq,
                    mfm.moduleId,
                    f.id,
                    mfm.status
                )
                from Features f
                left join Features pf on pf.id = f.parentFeatureId and pf.status = vn.viettel.core.enums.DBStatus.ACTIVE
                left join ModuleFeatureMap mfm
                    on mfm.featureId = f.id
                    and mfm.moduleId = :moduleId
                    and mfm.status = vn.viettel.core.enums.DBStatus.ACTIVE
                where 1 = 1
                    and f.status != vn.viettel.core.enums.DBStatus.INACTIVE
                    and 1 = (
                        case
                        when (:isInModule = true and mfm.moduleId is not null) then 1
                        when (:isInModule = false and mfm.moduleId is null) then 1
                        else 0
                        end
                    )
                    and ( :#{#featureQuery.name} is null
                        or f.name like %:#{#featureQuery.name}%
                        or f.code like %:#{#featureQuery.name}%
                        )
                    and ( :#{#featureQuery.description} is null or f.description like %:#{#featureQuery.description}% )
                    and ( :#{#featureQuery.parentId} is null or f.parentFeatureId = :#{#featureQuery.description} )
                    and ( :#{#featureQuery.featureType} is null or f.featureType = :#{#featureQuery.featureType} )
                    and ( :#{#featureQuery.status} is null or f.status = :#{#featureQuery.status} )
            """)
    Page<FeatureModuleData> getFeatureInModule(String moduleId, Boolean isInModule, FeatureQuery featureQuery, Pageable pageable);

}
