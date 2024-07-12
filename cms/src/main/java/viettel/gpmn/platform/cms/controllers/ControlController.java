package viettel.gpmn.platform.cms.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.controls.ControlData;
import viettel.gpmn.platform.cms.data.controls.ControlQuery;
import viettel.gpmn.platform.cms.services.ControlService;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.BaseData;
import viettel.gpmn.platform.core.data.response.Response;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/controls"})
public class ControlController extends BaseController {

    private ControlService controlService;

    @Operation(summary = "Get page of controls in a feature")
    @GetMapping
    public Response getListControlsByFeature(
            @RequestParam @NotNull String featureId,
            ControlQuery controlQuery,
            Pageable pageable
    ) {
        return Response.ok(controlService.getListControlsByFeatureId(featureId, controlQuery, pageable));
    }

    @Operation(summary = "Create controls in a feature")
    @PostMapping
    public Response createControls(@RequestBody List<ControlData> listControlData) {
        controlService.create(listControlData);
        return Response.ok();
    }

    @Operation(summary = "Update controls in a feature")
    @PutMapping
    public Response updateControls(@RequestBody List<ControlData> listControlData) {
        controlService.update(listControlData);
        return Response.ok();
    }

    @Operation(summary = "Update status of controls in a feature")
    @PutMapping(value = {"/status"})
    public Response updateControlsStatus(@RequestBody List<BaseData> listControlData) {
        controlService.updateStatus(listControlData);
        return Response.ok();
    }

    @Operation(summary = "Get page attribute of controls in a feature")
    @GetMapping(value = {"/attribute"})
    public Response getListControlAttribute(
            @RequestParam @NotBlank String featureId,
            Pageable pageable
    ) {
        return Response.ok(controlService.getListControlAttribute(featureId, pageable));
    }

}
