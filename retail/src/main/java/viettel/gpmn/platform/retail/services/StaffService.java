package viettel.gpmn.platform.retail.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel.gpmn.platform.core.services.BaseService;
import viettel.gpmn.platform.retail.data.staff.StaffData;
import viettel.gpmn.platform.retail.data.staff.StaffQuery;
import viettel.gpmn.platform.retail.data.staff.StaffRoleData;
import viettel.gpmn.platform.retail.repositories.StaffRepository;
import viettel.gpmn.platform.retail.repositories.StaffRoleMapRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StaffService extends BaseService {

    private StaffRepository staffRepository;
    private StaffRoleMapRepository staffRoleMapRepository;

    public Page<StaffData> getListStaff(StaffQuery staffQuery, Pageable pageable) {
        return staffRepository.getListStaff(staffQuery, pageable);
    }

    public Page<StaffRoleData> getListStaffRole(String staffId, Pageable pageable) {
        return staffRoleMapRepository.getListStaffRole(staffId, pageable);
    }

    @Transactional
    public void createStaffRole(List<StaffRoleData> listStaffRole) {
        listStaffRole = listStaffRole
                .stream()
                .filter(staffRoleData -> staffRoleData.getId() == null)
                .collect(Collectors.toList());
        List<StaffRoleMap> listStaffRoleMap = this.modelMapper.map(
                listStaffRole,
                new TypeToken<List<StaffRoleMap>>() {}.getType()
        );
        staffRoleMapRepository.saveAll(listStaffRoleMap);
    }

    @Transactional
    public void updateStaffRole(List<StaffRoleData> listStaffRole) {
        listStaffRole
                .stream()
                .filter(staffRoleData -> staffRoleData.getId() != null)
                .forEach(staffRoleData ->
                        staffRoleMapRepository.updateStaffRoleStatus(staffRoleData.getId(), staffRoleData.getStatus())
                );
    }


}
