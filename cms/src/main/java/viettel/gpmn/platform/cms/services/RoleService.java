package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel.gpmn.platform.cms.data.role.RoleData;
import viettel.gpmn.platform.cms.data.role.RoleFeatureMapData;
import viettel.gpmn.platform.cms.data.role.RoleQuery;
import viettel.gpmn.platform.cms.data.role.StaffRoleData;
import viettel.gpmn.platform.cms.entities.RoleFeatureMap;
import viettel.gpmn.platform.cms.entities.Roles;
import viettel.gpmn.platform.cms.entities.StaffRoleMap;
import viettel.gpmn.platform.cms.repositories.RoleFeatureMapRepository;
import viettel.gpmn.platform.cms.repositories.RoleRepository;
import viettel.gpmn.platform.cms.repositories.StaffRoleMapRepository;
import viettel.gpmn.platform.core.services.GenericSaveService;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService extends GenericSaveService<Roles, RoleData, RoleRepository> {

    private StaffRoleMapRepository staffRoleMapRepository;
    private RoleFeatureMapRepository roleFeatureMapRepository;

    public Page<RoleData> getListRole(RoleQuery roleQuery, Pageable pageable) {
        Page<Roles> pageRoles = this.repository.getListRole(roleQuery, pageable);
        List<RoleData> listRoleData = this.modelMapper.map(
                pageRoles.getContent(),
                new TypeToken<List<RoleData>>() {}.getType()
        );
        return new PageImpl<>(listRoleData, pageable, pageRoles.getTotalElements());
    }

    public Page<RoleData> getListRoleByStaffId(String staffId, RoleQuery roleQuery, Pageable pageable) {
        Page<Roles> pageRoles = this.repository.getListRoleByStaffId(staffId, roleQuery, pageable);
        List<RoleData> listRoleData = this.modelMapper.map(
                pageRoles.getContent(),
                new TypeToken<List<RoleData>>() {}.getType()
        );
        return new PageImpl<>(listRoleData, pageable, pageRoles.getTotalElements());
    }

    @Transactional
    public void saveRole2Staff(List<StaffRoleData> listStaffRoleData) {
        if (listStaffRoleData != null && !listStaffRoleData.isEmpty()) {
            List<StaffRoleMap> listStaffRoleMap = this.modelMapper.map(listStaffRoleData,
                    new TypeToken<List<StaffRoleMap>>() {}.getType());
            staffRoleMapRepository.saveAll(listStaffRoleMap);
        }
    }

    public List<RoleData> getListRoleExcludeStaffId(String staffId) {
        List<Roles> listRoles = this.repository.getListRoleExcludeStaffId(staffId);
        return this.modelMapper.map(listRoles, new TypeToken<List<RoleData>>() {}.getType());
    }

    public RoleData getRoleDetail(String roleId) {
        Roles role = this.repository.findById(roleId).orElseThrow();
        return this.modelMapper.map(role, RoleData.class);
    }

    @Override
    public List<Roles> save(List<RoleData> listData) {
        if (listData != null && !listData.isEmpty()) {
            return this.repository.saveAll(
                    this.modelMapper.map(listData, new TypeToken<List<Roles>>() {}.getType())
            );
        }
        return null;
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
