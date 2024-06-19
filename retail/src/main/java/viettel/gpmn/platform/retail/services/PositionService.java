package viettel.gpmn.platform.retail.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import viettel.gpmn.platform.core.services.BaseService;
import viettel.gpmn.platform.retail.repositories.PositionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PositionService extends BaseService {

    private PositionRepository positionRepository;

    public List<String> getListPositionName() {
        return positionRepository.getAllName();
    }

}
