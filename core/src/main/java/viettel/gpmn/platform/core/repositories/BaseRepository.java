package viettel.gpmn.platform.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import viettel.gpmn.platform.core.entities.EntityWithULID;

public interface BaseRepository<T extends EntityWithULID> extends JpaRepository<T, String> {
}
