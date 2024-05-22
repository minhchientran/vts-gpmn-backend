package vn.viettel.cms.controllers;

import vn.viettel.cms.services.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/common"})
public class CommonController extends BaseController {

    private final CommonService commonService;

    @GetMapping(value = {"/status"})
    public Response getListStatus() {
        return Response.ok(commonService.getListStatus());
    }

    @GetMapping(value = {"/subsystem"})
    public Response getListSubsystem() {
        return Response.ok(commonService.getListSubsystem());
    }
}
