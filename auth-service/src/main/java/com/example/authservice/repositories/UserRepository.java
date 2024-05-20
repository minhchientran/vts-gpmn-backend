package com.example.authservice.repositories;

import com.example.authservice.entities.Users;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.repository.Query;
import vn.viettel.core.data.users.UserTokenData;
import vn.viettel.core.enums.Subsystem;
import vn.viettel.core.repositories.BaseRepository;

public interface UserRepository extends BaseRepository<Users> {
    @Query("SELECT new vn.viettel.core.data.users.UserTokenData ( " +
            "   u.username, " +
            "   u.password, " +
            "   u.id, " +
            "   usm.subsystem, " +
            "   s.id, " +
            "   s.identityCode, " +
            "   coalesce(null, null) " +
            ") " +
            "FROM Users u " +
            "JOIN UserSupplierMap usm " +
            "   ON u.id = usm.userId " +
            "   AND usm.subsystem = :subsystem " +
            "   AND usm.status = vn.viettel.core.enums.DBStatus.ACTIVE " +
            "JOIN Suppliers s ON s.id = usm.supplierId AND s.status = vn.viettel.core.enums.DBStatus.ACTIVE " +
            "WHERE 1 = 1 " +
            "   AND u.username = :username " +
            "   AND u.status = vn.viettel.core.enums.DBStatus.ACTIVE ")
    UserTokenData findByUsernameAndSubsystem(String username, Subsystem subsystem);
}
