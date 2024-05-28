package vn.viettel.cms.controllers;

import vn.viettel.cms.services.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;
import vn.viettel.core.enums.ControlType;
import vn.viettel.core.enums.DBStatus;
import vn.viettel.core.enums.FeatureType;
import vn.viettel.core.enums.Subsystem;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/common"})
public class CommonController extends BaseController {

    private final CommonService commonService;

    @GetMapping(value = {"/status"})
    public Response getListStatus() {
        return Response.ok(commonService.getListEnumInfo(DBStatus.class));
    }

    @GetMapping(value = {"/subsystems"})
    public Response getListSubsystem() {
        return Response.ok(commonService.getListEnumInfo(Subsystem.class));
    }

    @GetMapping(value = {"/controlTypes"})
    public Response getListControlType() {
        return Response.ok(commonService.getListEnumInfo(ControlType.class));
    }

    @GetMapping(value = {"/featureTypes"})
    public Response getListFeatureType() {
        return Response.ok(commonService.getListEnumInfo(FeatureType.class));
    }
}
