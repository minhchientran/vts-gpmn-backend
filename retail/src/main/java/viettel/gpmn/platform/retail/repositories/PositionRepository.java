package viettel.gpmn.platform.retail.repositories;

import org.springframework.data.jpa.repository.Query;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.repositories.BaseRepository;
import viettel.gpmn.platform.retail.data.PositionData;
import viettel.gpmn.platform.retail.entities.Positions;

import java.util.List;

public interface PositionRepository extends BaseRepository<Positions> {

    @Query(value = "select new viettel.gpmn.platform.retail.data.PositionData(p.id, p.name) from Positions p")
    List<PositionData> getAllName();
}
