package vn.viettel.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.viettel.core.entities.EntityWithULID;

public interface BaseRepository<T extends EntityWithULID> extends JpaRepository<T, String> {
}
