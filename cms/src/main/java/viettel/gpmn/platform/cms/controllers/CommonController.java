package viettel.gpmn.platform.cms.controllers;

import viettel.gpmn.platform.cms.enums.ControlType;
import viettel.gpmn.platform.cms.enums.FeatureType;
import viettel.gpmn.platform.cms.services.CommonService;
import viettel.gpmn.platform.cms.enums.BusinessDomain;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.response.Response;
import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.enums.Subsystem;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/common"})
public class CommonController extends BaseController {

    private CommonService commonService;

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

    @GetMapping(value = {"/businessDomains"})
    public Response getListBusinessDomain() {
        return Response.ok(commonService.getListEnumInfo(BusinessDomain.class));
    }
}
