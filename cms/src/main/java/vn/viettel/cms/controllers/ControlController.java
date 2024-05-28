package vn.viettel.cms.controllers;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.viettel.cms.data.controls.ControlData;
import vn.viettel.cms.services.ControlService;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/controls"})
public class ControlController extends BaseController {

    private ControlService controlService;

    @GetMapping
    public Response getListControlByFeature(
            @RequestParam @NotNull String featureId,
            Pageable pageable) {
        return Response.ok(controlService.getListControlByFeatureId(featureId, pageable));
    }

    @PostMapping
    public Response createControls(
            @RequestParam @NotNull String featureId,
            @RequestBody List<ControlData> listControlData) {
        controlService.createControls(featureId, listControlData);
        return Response.ok();
    }

    @PutMapping
    public Response updateControls(
            @RequestBody List<ControlData> listControlData) {
        controlService.updateControls(listControlData);
        return Response.ok();
    }

}
