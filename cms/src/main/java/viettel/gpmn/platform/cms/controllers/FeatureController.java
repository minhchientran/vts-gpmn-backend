package viettel.gpmn.platform.cms.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.features.FeatureData;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.entities.Features;
import viettel.gpmn.platform.cms.services.FeatureService;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.controllers.GenericSaveController;
import viettel.gpmn.platform.core.data.response.Response;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/features"})
public class FeatureController extends BaseController {
    
    private FeatureService featureService;
    
    @GetMapping
    public Response getListFeatures(
            FeatureQuery featureQuery,
            Pageable pageable) {
        return Response.ok(featureService.getListFeatures(featureQuery, pageable));
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Response saveFeature(@RequestBody List<FeatureData> listFeatureData) {
        featureService.save(listFeatureData);
        return Response.ok();
    }

    @GetMapping(value = {"/parents"})
    public Response getListParentFeature() {
        return Response.ok(featureService.getListParentFeature());
    }

    @GetMapping(value = {"/{featureId}"})
    public Response getFeatureDetail(@PathVariable(value = "featureId") String featureId) {
        return Response.ok(featureService.getFeatureDetail(featureId));
    }
    
}
