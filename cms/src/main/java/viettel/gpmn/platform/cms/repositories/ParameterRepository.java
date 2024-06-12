package viettel.gpmn.platform.cms.repositories;

import viettel.gpmn.platform.cms.entities.Parameters;
import viettel.gpmn.platform.core.repositories.BaseRepository;

import java.util.Optional;

public interface ParameterRepository extends BaseRepository<Parameters> {

    Optional<Parameters> findByCodeAndStatusIsTrue(String code);

}
