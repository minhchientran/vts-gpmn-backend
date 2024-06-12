package viettel.gpmn.platform.cms.repositories;

import viettel.gpmn.platform.cms.entities.Users;
import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.core.data.users.UserTokenData;
import viettel.gpmn.platform.core.enums.Subsystem;
import viettel.gpmn.platform.core.repositories.BaseRepository;

public interface UserRepository extends BaseRepository<Users> {
    @Query("SELECT new viettel.gpmn.platform.core.data.users.UserTokenData ( " +
            "   u.username, " +
            "   u.password, " +
            "   u.id, " +
            "   usm.subsystem, " +
            "   s.id, " +
            "   s.code " +
            ") " +
            "FROM Users u " +
            "JOIN UserSupplierMap usm " +
            "   ON u.id = usm.userId " +
            "   AND usm.subsystem = :subsystem " +
            "   AND usm.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE " +
            "JOIN Suppliers s ON s.id = usm.supplierId AND s.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE " +
            "WHERE 1 = 1 " +
            "   AND u.username = :username " +
            "   AND u.status = viettel.gpmn.platform.core.enums.DBStatus.ACTIVE ")
    UserTokenData findByUsernameAndSubsystem(String username, Subsystem subsystem);
}
