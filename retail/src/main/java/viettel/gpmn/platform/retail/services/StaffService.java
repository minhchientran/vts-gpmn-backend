package viettel.gpmn.platform.retail.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import viettel.gpmn.platform.core.services.BaseService;
import viettel.gpmn.platform.retail.data.staff.StaffData;
import viettel.gpmn.platform.retail.data.staff.StaffQuery;
import viettel.gpmn.platform.retail.repositories.StaffRepository;

@Service
@AllArgsConstructor
public class StaffService extends BaseService {

    private StaffRepository staffRepository;

    public Page<StaffData> getListStaff(StaffQuery staffQuery, Pageable pageable) {
        return staffRepository.getListStaff(staffQuery, pageable);
    }

}
