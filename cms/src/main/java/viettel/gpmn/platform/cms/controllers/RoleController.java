package viettel.gpmn.platform.cms.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.data.role.*;
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

    @Operation(summary = "Get page of roles")
    @GetMapping
    public Response getListRole(RoleQuery roleQuery, Pageable pageable
    ) {
        return Response.ok(roleService.getListRole(roleQuery, pageable));
    }

    @Operation(summary = "Create or update roles")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Response saveRoles(@RequestBody List<RoleData> listRoleData) {
        roleService.save(listRoleData);
        return Response.ok();
    }

    @Operation(summary = "Update status of roles mapping in a staff")
    @PutMapping(value = {"/staff/status"})
    public Response updateStaffRolesStatus(@RequestBody List<BaseData> listRoleData) {
        roleService.updateStaffRoleStatus(listRoleData);
        return Response.ok();
    }

    @Operation(summary = "Update status of roles")
    @PutMapping(value = {"/status"})
    public Response updateRolesStatus(@RequestBody List<BaseData> listRoleData) {
        roleService.updateRolesStatus(listRoleData);
        return Response.ok();
    }

    @Operation(summary = "Get page of roles in a staff")
    @GetMapping(value = {"/staff"})
    public Response getListRoleByStaffId(
            @RequestParam String staffId,
            RoleQuery roleQuery,
            Pageable pageable
    ) {
        return Response.ok(roleService.getListRoleByStaffId(staffId, roleQuery, pageable));
    }

    @Operation(summary = "Get list of roles not in a staff")
    @GetMapping(value = {"/staffExcluded"})
    public Response getListRoleExcludeStaffId(@RequestParam String staffId) {
        return Response.ok(roleService.getListRoleExcludeStaffId(staffId));
    }

    @Operation(summary = "Add roles to a staff")
    @PostMapping(value = {"/staff"})
    public Response addRole2Staff(@RequestBody List<StaffRoleData> listStaffRoleData) {
        roleService.saveRole2Staff(listStaffRoleData);
        return Response.ok();
    }

    @Operation(summary = "Update info of a role mapping in a staff")
    @PutMapping(value = {"/staff/{staffRoleId}"})
    public Response updateRole2Staff(
            @PathVariable(value = "staffRoleId") String staffRoleId,
            @RequestBody StaffRoleUpdateData staffRoleData) {
        roleService.updateRole2Staff(staffRoleId, staffRoleData);
        return Response.ok();
    }

    @Operation(summary = "Get info of a role by role id")
    @GetMapping(value = {"/{roleId}"})
    public Response getRoleDetail(@PathVariable String roleId) {
        return Response.ok(roleService.getRoleDetail(roleId));
    }

    @Operation(summary = "Get list of features in a roles")
    @GetMapping(value = {"/{roleId}/features"})
    public Response getListFeatureByRoleId(@PathVariable String roleId, FeatureQuery featureQuery, Pageable pageable) {
        return Response.ok(featureService.getListFeatureByRole(roleId, featureQuery, pageable));
    }

    @Operation(summary = "Get list of features not in a roles")
    @GetMapping(value = {"/{roleId}/featuresHierarchy"})
    public Response getListFeatureHierarchy(@PathVariable String roleId) {
        return Response.ok(featureService.getListFeatureHierarchy(roleId));
    }

    @Operation(summary = "Add features to a roles")
    @PostMapping(value = {"/features"})
    public Response saveFeature2Role(@RequestBody List<RoleFeatureMapData> listRoleFeatureMapData) {
        roleService.saveFeature2Role(listRoleFeatureMapData);
        return Response.ok();
    }

}
