package viettel.gpmn.platform.cms.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.gpmn.platform.cms.data.features.FeatureData;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.entities.Features;
import viettel.gpmn.platform.cms.services.FeatureService;
import viettel.gpmn.platform.core.controllers.GenericSaveController;
import viettel.gpmn.platform.core.data.response.Response;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/features"})
public class FeatureController extends GenericSaveController<Features, FeatureData, FeatureService> {

    @GetMapping
    public Response getListFeatures(
            FeatureQuery featureQuery,
            Pageable pageable) {
        return Response.ok(this.service.getListFeatures(featureQuery, pageable));
    }

    @GetMapping(value = {"/parents"})
    public Response getListParentFeature() {
        return Response.ok(this.service.getListParentFeature());
    }

    @GetMapping(value = {"/{id}"})
    public Response getFeatureDetail(
            @PathVariable(value = "id") String featureId
    ) {
        return Response.ok(this.service.getFeatureDetail(featureId));
    }
}
