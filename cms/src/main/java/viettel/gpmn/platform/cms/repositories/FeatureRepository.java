package viettel.gpmn.platform.cms.repositories;

import viettel.gpmn.platform.cms.data.features.FeatureModuleData;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.data.features.FeatureData;
import viettel.gpmn.platform.cms.entities.Features;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.core.data.users.UserFeatureData;
import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.repositories.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface FeatureRepository extends BaseRepository<Features> {

    List<Features> getAllByStatus(DBStatus status);

    Optional<Features> findByIdAndStatus(String id, DBStatus status);

    @Query(value = """
               select new viettel.gpmn.platform.core.data.users.UserFeatureData (
                   f.id,
                   f.code,
                   c.id,
                   c.code
                )
                from Features f
                left join Controls c on c.featureId = f.id and c.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                join ModuleFeatureMap mfm on mfm.featureId = f.id and mfm.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                join Modules m on m.id = mfm.moduleId and m.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                join SupplierModuleMap smm on smm.moduleId = m.id and smm.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                join Suppliers s on s.id = smm.supplierId and s.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                join UserSupplierMap usm on usm.supplierId = s.id and usm.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                join Users u on u.id = usm.userId and u.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                where 1 = 1
                   and u.id = :userId
               and s.id = :supplierId
            """)
    List<UserFeatureData> getAdminFeatures(String userId, String supplierId);

    @Query(value = """
                select new viettel.gpmn.platform.cms.data.features.FeatureData(
                    f.id,
                    f.code,
                    f.name,
                    pf.code,
                    f.featureType,
                    f.description,
                    f.url,
                    f.seq,
                    f.status
                )
                from Features f
                left join Features pf on pf.id = f.parentFeatureId and pf.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                where 1 = 1
                    and ( :#{#featureQuery.name} is null
                        or f.name like %:#{#featureQuery.name}%
                        or f.code like %:#{#featureQuery.name}%
                        )
                    and ( :#{#featureQuery.parentId} is null or f.parentFeatureId = :#{#featureQuery.parentId} )
                    and ( :#{#featureQuery.featureType} is null or f.featureType = :#{#featureQuery.featureType} )
                    and ( :#{#featureQuery.status} is null or f.status = :#{#featureQuery.status} )
            """)
    Page<FeatureData> getListFeature(FeatureQuery featureQuery, Pageable pageable);

    @Query(value = """
                select distinct new viettel.gpmn.platform.cms.data.features.FeatureModuleData(
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
                left join Features pf on pf.id = f.parentFeatureId and pf.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                left join ModuleFeatureMap mfm
                    on mfm.featureId = f.id
                    and mfm.moduleId = :moduleId
                    and mfm.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                where 1 = 1
                    and f.status != viettel.gpmn.platform.core.enums.DBStatus.INACTIVE
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
                    and ( :#{#featureQuery.parentId} is null or f.parentFeatureId = :#{#featureQuery.parentId} )
                    and ( :#{#featureQuery.featureType} is null or f.featureType = :#{#featureQuery.featureType} )
                    and ( :#{#featureQuery.status} is null or f.status = :#{#featureQuery.status} )
            """)
    Page<FeatureModuleData> getFeatureInModule(String moduleId, Boolean isInModule, FeatureQuery featureQuery, Pageable pageable);

    @Query(value = """
                    select distinct new viettel.gpmn.platform.cms.data.features.FeatureModuleData(
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
                    join RoleFeatureMap rfm
                        on rfm.featureId = f.id
                        and rfm.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                    left join Features pf
                        on pf.id = f.parentFeatureId
                        and pf.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                    left join ModuleFeatureMap mfm
                        on mfm.featureId = f.id
                        and mfm.moduleId = :moduleId
                        and mfm.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
                    where 1 = 1
                        and rfm.roleId = :roleId
                        and ( :#{#featureQuery.name} is null
                            or f.name like %:#{#featureQuery.name}%
                            or f.code like %:#{#featureQuery.name}%
                            )
                        and ( :#{#featureQuery.parentId} is null or f.parentFeatureId = :#{#featureQuery.parentId} )
                        and ( :#{#featureQuery.featureType} is null or f.featureType = :#{#featureQuery.featureType} )
                        and ( :#{#featureQuery.status} is null or f.status = :#{#featureQuery.status} )
    """)
    Page<FeatureModuleData> getListFeatureByRole(String roleId, FeatureQuery featureQuery, Pageable pageable);

    @Query(value = """
        select f
        from Features f
        left join RoleFeatureMap rfm
            on rfm.featureId = f.id
            and rfm.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE
        where 1 = 1
            and rfm.roleId <> :roleId
    """)
    List<Features> getListFeatureExcludeRoleId(String roleId);
}
