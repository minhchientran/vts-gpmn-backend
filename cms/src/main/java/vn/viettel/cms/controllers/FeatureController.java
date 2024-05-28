package vn.viettel.cms.controllers;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import vn.viettel.cms.data.features.FeatureData;
import vn.viettel.cms.data.features.FeatureQuery;
import vn.viettel.cms.services.FeatureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.viettel.core.data.response.Response;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/features"})
public class FeatureController {

    private final FeatureService featureService;

    @GetMapping
    public Response getListFeature(FeatureQuery featureQuery, Pageable pageable) {
        return Response.ok(featureService.getListFeature(featureQuery, pageable));
    }

    @GetMapping(value = {"/parents"})
    public Response getListParentFeature() {
        return Response.ok(featureService.getListParentFeature());
    }

    @PostMapping
    public Response createFeatures(
            @RequestBody @NotEmpty List<FeatureData> listFeatureData) {
        featureService.createFeatures(listFeatureData);
        return Response.ok();
    }

    @PutMapping
    public Response updateFeatures(
            @RequestBody @NotEmpty List<FeatureData> listFeatureData) {
        featureService.updateFeatures(listFeatureData);
        return Response.ok();
    }

}
