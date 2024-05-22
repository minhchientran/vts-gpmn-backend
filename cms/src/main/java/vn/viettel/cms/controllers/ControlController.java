package vn.viettel.cms.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import vn.viettel.cms.data.controls.ControlData;
import vn.viettel.cms.services.ControlService;
import vn.viettel.core.data.response.Response;


@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/control"})
public class ControlController {

    private ControlService controlService;

    @GetMapping(value = {"/controls"})
    public Response getListControlByFeature(@RequestParam String featureId, Pageable pageable) {
        return Response.ok(controlService.getListControlByFeatureId(featureId, pageable));
    }

    @PostMapping
    public Response createControls(@RequestParam String featureId, @RequestParam ControlData controlData) {
        controlService.createControls(featureId, controlData);
        return Response.ok();
    }
}
