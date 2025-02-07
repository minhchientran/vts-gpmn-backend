package viettel.gpmn.platform.cms.services;

import io.jsonwebtoken.lang.Objects;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel.gpmn.platform.cms.data.role.*;
import viettel.gpmn.platform.cms.entities.RoleFeatureMap;
import viettel.gpmn.platform.cms.entities.Roles;
import viettel.gpmn.platform.cms.entities.StaffRoleMap;
import viettel.gpmn.platform.cms.repositories.RoleFeatureMapRepository;
import viettel.gpmn.platform.cms.repositories.RoleRepository;
import viettel.gpmn.platform.cms.repositories.StaffRoleMapRepository;
import viettel.gpmn.platform.core.data.BaseData;
import viettel.gpmn.platform.core.services.BaseService;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService extends BaseService {
    
    private StaffRoleMapRepository staffRoleMapRepository;
    private RoleRepository roleRepository;
    private RoleFeatureMapRepository roleFeatureMapRepository;

    public Page<RoleData> getListRole(RoleQuery roleQuery, Pageable pageable) {
        Page<Roles> pageRoles = roleRepository.getListRole(roleQuery, pageable);
        List<RoleData> listRoleData = this.modelMapper.map(
                pageRoles.getContent(),
                new TypeToken<List<RoleData>>() {}.getType()
        );
        return new PageImpl<>(listRoleData, pageable, pageRoles.getTotalElements());
    }

    public Page<RoleData> getListRoleByStaffId(String staffId, RoleQuery roleQuery, Pageable pageable) {
        return roleRepository.getListRoleByStaffId(staffId, roleQuery, pageable);
    }

    @Transactional
    public void saveRole2Staff(List<StaffRoleData> listStaffRoleData) {
        if (listStaffRoleData != null && !listStaffRoleData.isEmpty()) {
            List<StaffRoleMap> listStaffRoleMap = this.modelMapper.map(listStaffRoleData,
                    new TypeToken<List<StaffRoleMap>>() {}.getType());
            staffRoleMapRepository.saveAll(listStaffRoleMap);
        }
    }

    @Transactional
    public void updateRole2Staff(String staffRoleId, StaffRoleUpdateData staffRoleData) {
        if (staffRoleId == null || staffRoleId.isEmpty() || Objects.isEmpty(staffRoleData)) {
            return;
        }

        StaffRoleMap staffRoleMapData = this.modelMapper.map(staffRoleData, new TypeToken<StaffRoleMap>() {}.getType());
        staffRoleMapRepository.updateStaffRoleMap(staffRoleId, staffRoleMapData);
    }

    public List<RoleData> getListRoleExcludeStaffId(String staffId) {
        List<Roles> listRoles = roleRepository.getListRoleExcludeStaffId(staffId);
        return this.modelMapper.map(listRoles, new TypeToken<List<RoleData>>() {}.getType());
    }

    public RoleData getRoleDetail(String roleId) {
        Roles role = roleRepository.findById(roleId).orElseThrow();
        return this.modelMapper.map(role, RoleData.class);
    }

    @Transactional
    public void save(List<RoleData> listData) {
        roleRepository.saveAll(
                this.modelMapper.map(listData, new TypeToken<List<Roles>>() {}.getType())
        );
    }

    @Transactional
    public void updateStaffRoleStatus(List<BaseData> listRoleData) {
        listRoleData.forEach(roleData -> roleRepository.updateStaffRoleStatus(roleData.getId(), roleData.getStatus()));
    }

    @Transactional
    public void updateRolesStatus(List<BaseData> listRoleData) {
        listRoleData.forEach(roleData -> roleRepository.updateStatus(roleData.getId(), roleData.getStatus()));
    }


    @Transactional
    public void saveFeature2Role(List<RoleFeatureMapData> listRoleFeatureMapData) {
        if (listRoleFeatureMapData != null && !listRoleFeatureMapData.isEmpty()) {
            List<RoleFeatureMap> listRoleFeatureMap = this.modelMapper.map(listRoleFeatureMapData,
                    new TypeToken<List<RoleFeatureMap>>() {}.getType());
            roleFeatureMapRepository.saveAll(listRoleFeatureMap);
        }
    }
}
