package viettel.gpmn.platform.cms.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.features.FeatureData;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.services.FeatureService;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.BaseData;
import viettel.gpmn.platform.core.data.response.Response;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/features"})
public class FeatureController extends BaseController {
    
    private FeatureService featureService;

    @Operation(summary = "Get page of features")
    @GetMapping
    public Response getListFeatures(
            FeatureQuery featureQuery,
            Pageable pageable
    ) {
        return Response.ok(featureService.getListFeatures(featureQuery, pageable));
    }

    @Operation(summary = "Create or update info of features")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Response saveFeature(@RequestBody List<FeatureData> listFeatureData) {
        featureService.save(listFeatureData);
        return Response.ok();
    }

    @Operation(summary = "Create or updates of features")
    @PutMapping(value = {"/status"})
    public Response updateFeatureStatus(@RequestBody List<BaseData> listFeatureData) {
        featureService.updateFeatureStatus(listFeatureData);
        return Response.ok();
    }

    @Operation(summary = "Get list of all parent features")
    @GetMapping(value = {"/parents"})
    public Response getListParentFeature() {
        return Response.ok(featureService.getListParentFeature());
    }

    @Operation(summary = "Get detail of a feature by feature id")
    @GetMapping(value = {"/{featureId}"})
    public Response getFeatureDetail(@PathVariable(value = "featureId") String featureId) {
        return Response.ok(featureService.getFeatureDetail(featureId));
    }
    
}
