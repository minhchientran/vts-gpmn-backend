package viettel.gpmn.platform.core.configs.jpa;

import com.github.f4b6a3.ulid.UlidCreator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import viettel.gpmn.platform.core.entities.EntityWithULID;

public class ULIDGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        if (EntityWithULID.class.isAssignableFrom(o.getClass()) && ((EntityWithULID) o).getId() != null) {
            return ((EntityWithULID) o).getId();
        }
        return UlidCreator.getUlid().toString();
    }
}
