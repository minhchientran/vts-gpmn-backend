package viettel.gpmn.platform.cms.controllers;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import viettel.gpmn.platform.cms.data.controls.ControlData;
import viettel.gpmn.platform.cms.data.controls.ControlQuery;
import viettel.gpmn.platform.cms.entities.Controls;
import viettel.gpmn.platform.cms.services.ControlService;
import viettel.gpmn.platform.core.controllers.GenericSaveController;
import viettel.gpmn.platform.core.data.response.Response;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/controls"})
public class ControlController extends GenericSaveController<Controls, ControlData, ControlService> {

    @GetMapping
    public Response getListControlsByFeature(
            @RequestParam @NotNull String featureId,
            ControlQuery controlQuery,
            Pageable pageable) {
        return Response.ok(this.service.getListControlsByFeatureId(featureId, controlQuery, pageable));
    }

}
