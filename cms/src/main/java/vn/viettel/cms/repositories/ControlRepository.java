package vn.viettel.cms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.viettel.cms.entities.Controls;
import vn.viettel.core.repositories.BaseRepository;

public interface ControlRepository extends BaseRepository<Controls> {

    Page<Controls> getAllByFeatureId(String featureId, Pageable pageable);
}
