package viettel.gpmn.platform.cms.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.data.role.RoleData;
import viettel.gpmn.platform.cms.data.role.RoleFeatureMapData;
import viettel.gpmn.platform.cms.data.role.RoleQuery;
import viettel.gpmn.platform.cms.data.role.StaffRoleData;
import viettel.gpmn.platform.cms.entities.Roles;
import viettel.gpmn.platform.cms.services.FeatureService;
import viettel.gpmn.platform.cms.services.RoleService;
import viettel.gpmn.platform.core.controllers.GenericSaveController;
import viettel.gpmn.platform.core.data.response.Response;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/roles")
public class RoleController extends GenericSaveController<Roles, RoleData, RoleService> {

    private FeatureService featureService;

    @GetMapping
    public Response getListRole(RoleQuery roleQuery, Pageable pageable
    ) {
        return Response.ok(this.service.getListRole(roleQuery, pageable));
    }

    @GetMapping(value = {"/staff"})
    public Response getListRoleByStaffId(
            @RequestParam String staffId,
            RoleQuery roleQuery,
            Pageable pageable
    ) {
        return Response.ok(this.service.getListRoleByStaffId(staffId, roleQuery, pageable));
    }

    @GetMapping(value = {"/staffExcluded"})
    public Response getListRoleExcludeStaffId(@RequestParam String staffId) {
        return Response.ok(this.service.getListRoleExcludeStaffId(staffId));
    }

    @PostMapping(value = {"/staff"})
    public Response addRole2Staff(@RequestBody List<StaffRoleData> listStaffRoleData) {
        this.service.saveRole2Staff(listStaffRoleData);
        return Response.ok();
    }

    @GetMapping(value = {"/{roleId}"})
    public Response getRoleDetail(@PathVariable String roleId) {
        return Response.ok(this.service.getRoleDetail(roleId));
    }

    @GetMapping(value = {"/features"})
    public Response getListFeatureByRoleId(String rolesId, FeatureQuery featureQuery, Pageable pageable) {
        return Response.ok(featureService.getListFeatureByRole(rolesId, featureQuery, pageable));
    }

    @GetMapping(value = {"/featuresExcluded"})
    public Response getListFeatureExcludeRoleId(String rolesId) {
        return Response.ok(featureService.getListFeatureExcludeRoleId(rolesId));
    }

    @PostMapping(value = {"/features"})
    public Response saveFeature2Role(@RequestBody List<RoleFeatureMapData> listRoleFeatureMapData) {
        this.service.saveFeature2Role(listRoleFeatureMapData);
        return Response.ok();
    }

}
