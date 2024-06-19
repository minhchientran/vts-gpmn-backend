package viettel.gpmn.platform.retail.repositories;

import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.core.repositories.BaseRepository;
import viettel.gpmn.platform.retail.entities.Positions;

import java.util.List;

public interface PositionRepository extends BaseRepository<Positions> {

    @Query(value = "select p.name from Positions p")
    List<String> getAllName();
}
