package com.example.authservice.repositories;

import com.example.authservice.entities.Features;
import org.springframework.data.jpa.repository.Query;
import vn.viettel.core.data.users.UserFeatureData;
import vn.viettel.core.repositories.BaseRepository;

import java.util.List;

public interface FeaturesRepository extends BaseRepository<Features> {

    @Query(value = """
               select new vn.viettel.core.data.users.UserFeatureData(  
                   f.code,  
                   f.name,  
                   c.code,  
                   c.name  
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
    List<UserFeatureData> getFeatures(String userId, String supplierId);
}
