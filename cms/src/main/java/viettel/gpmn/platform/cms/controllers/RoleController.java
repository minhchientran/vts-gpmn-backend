package viettel.gpmn.platform.cms.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.data.role.RoleData;
import viettel.gpmn.platform.cms.data.role.RoleFeatureMapData;
import viettel.gpmn.platform.cms.data.role.RoleQuery;
import viettel.gpmn.platform.cms.data.role.StaffRoleData;
import viettel.gpmn.platform.cms.services.FeatureService;
import viettel.gpmn.platform.cms.services.RoleService;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.BaseData;
import viettel.gpmn.platform.core.data.response.Response;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/roles")
public class RoleController extends BaseController {
    
    private FeatureService featureService;
    private RoleService roleService;
    
    @GetMapping
    public Response getListRole(RoleQuery roleQuery, Pageable pageable
    ) {
        return Response.ok(roleService.getListRole(roleQuery, pageable));
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Response saveModules(@RequestBody List<RoleData> listRoleData) {
        roleService.save(listRoleData);
        return Response.ok();
    }

    @PutMapping(value = {"/status"})
    public Response updateRolesStatus(@RequestBody List<BaseData> listRoleData) {
        roleService.updateRolesStatus(listRoleData);
        return Response.ok();
    }

    @GetMapping(value = {"/staff"})
    public Response getListRoleByStaffId(
            @RequestParam String staffId,
            RoleQuery roleQuery,
            Pageable pageable
    ) {
        return Response.ok(roleService.getListRoleByStaffId(staffId, roleQuery, pageable));
    }

    @GetMapping(value = {"/staffExcluded"})
    public Response getListRoleExcludeStaffId(@RequestParam String staffId) {
        return Response.ok(roleService.getListRoleExcludeStaffId(staffId));
    }

    @PostMapping(value = {"/staff"})
    public Response addRole2Staff(@RequestBody List<StaffRoleData> listStaffRoleData) {
        roleService.saveRole2Staff(listStaffRoleData);
        return Response.ok();
    }

    @GetMapping(value = {"/{roleId}"})
    public Response getRoleDetail(@PathVariable String roleId) {
        return Response.ok(roleService.getRoleDetail(roleId));
    }

    @GetMapping(value = {"/{roleId}/features"})
    public Response getListFeatureByRoleId(@PathVariable String roleId, FeatureQuery featureQuery, Pageable pageable) {
        return Response.ok(featureService.getListFeatureByRole(roleId, featureQuery, pageable));
    }

    @GetMapping(value = {"/{roleId}/featuresHierarchy"})
    public Response getListFeatureHierarchy(@PathVariable String roleId) {
        return Response.ok(featureService.getListFeatureHierarchy(roleId));
    }

    @PostMapping(value = {"/features"})
    public Response saveFeature2Role(@RequestBody List<RoleFeatureMapData> listRoleFeatureMapData) {
        roleService.saveFeature2Role(listRoleFeatureMapData);
        return Response.ok();
    }

}
