package viettel.gpmn.platform.retail.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.response.Response;
import viettel.gpmn.platform.retail.data.staff.StaffQuery;
import viettel.gpmn.platform.retail.services.StaffService;


@AllArgsConstructor
@RestController
@RequestMapping("/v1/staffs")
public class StaffController extends BaseController {

    private StaffService staffService;

    @GetMapping
    public Response getListStaff(StaffQuery staffQuery, Pageable pageable) {
        return Response.ok(staffService.getListStaff(staffQuery, pageable));
    }

}
