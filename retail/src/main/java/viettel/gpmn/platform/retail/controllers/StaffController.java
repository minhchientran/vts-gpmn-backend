package viettel.gpmn.platform.retail.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.response.Response;
import viettel.gpmn.platform.retail.data.staff.StaffQuery;
import viettel.gpmn.platform.retail.data.staff.StaffRoleData;
import viettel.gpmn.platform.retail.services.StaffService;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/v1/staffs")
public class StaffController extends BaseController {

    private StaffService staffService;

    @GetMapping
    public Response getListStaff(StaffQuery staffQuery, Pageable pageable) {
        return Response.ok(staffService.getListStaff(staffQuery, pageable));
    }

    @GetMapping(value = {"/roles"})
    public Response getListStaffRole(
            @RequestParam String staffId,
            Pageable pageable
    ) {
        return Response.ok(staffService.getListStaffRole(staffId, pageable));
    }

    @PostMapping(value = {"/roles"})
    public Response createStaffRole(
            @RequestBody List<StaffRoleData> listStaffRole
    ) {
        staffService.createStaffRole(listStaffRole);
        return Response.ok();
    }

    @PutMapping(value = {"/roles"})
    public Response updateStaffRole(
            @RequestBody List<StaffRoleData> listStaffRole
    ) {
        staffService.updateStaffRole(listStaffRole);
        return Response.ok();
    }
}
