package vn.viettel.cms.repositories;

import vn.viettel.cms.entities.Parameters;
import vn.viettel.core.repositories.BaseRepository;

import java.util.Optional;

public interface ParameterRepository extends BaseRepository<Parameters> {

    Optional<Parameters> findByCodeAndStatusIsTrue(String code);

}
