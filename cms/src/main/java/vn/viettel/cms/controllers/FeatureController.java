package vn.viettel.cms.controllers;

import org.springframework.data.domain.Pageable;
import vn.viettel.cms.data.features.FeatureAddControlData;
import vn.viettel.cms.data.features.FeatureData;
import vn.viettel.cms.data.features.FeatureQuery;
import vn.viettel.cms.services.FeatureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.viettel.core.data.response.Response;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/feature"})
public class FeatureController {

    private final FeatureService featureService;
    @PostMapping
    public Response createFeature(@RequestBody FeatureData featureData) {
        featureService.createFeature(featureData);
        return Response.ok();
    }

    @GetMapping(value = {"/features"})
    public Response getListFeature(FeatureQuery featureQuery, Pageable pageable) {
        return Response.ok(featureService.getListFeature(featureQuery, pageable));
    }

    @PostMapping(value = {"/addControls"})
    public Response addControls2Feature(@RequestBody List<FeatureAddControlData> listFeatureAddControlData) {
        featureService.addControls2Feature(listFeatureAddControlData);
        return Response.ok();
    }

}
