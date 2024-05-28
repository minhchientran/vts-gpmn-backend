package vn.viettel.cms.controllers;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.viettel.cms.data.modules.ModuleAddFeatureData;
import vn.viettel.cms.data.modules.ModuleData;
import vn.viettel.cms.data.modules.ModuleQuery;
import vn.viettel.cms.services.FeatureService;
import vn.viettel.cms.services.ModuleService;
import lombok.AllArgsConstructor;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/modules"})
public class ModuleController extends BaseController {

    private final ModuleService moduleService;
    private final FeatureService featureService;

    @GetMapping
    public Response getListModule(ModuleQuery moduleQuery, Pageable pageable) {
        return Response.ok(moduleService.getListModule(moduleQuery, pageable));
    }

    @PostMapping
    public Response createModules(
            @RequestBody @NotEmpty List<ModuleData> listModuleData) {
        moduleService.createModules(listModuleData);
        return Response.ok();
    }

    @PutMapping
    public Response updateModules(
            @RequestBody @NotEmpty List<ModuleData> listModuleData) {
        moduleService.updateModules(listModuleData);
        return Response.ok();
    }

    @GetMapping(value = {"/features"})
    public Response getFeaturesInModule(
            @RequestParam @NotEmpty String moduleId,
            @RequestParam Boolean isInModule,
            Pageable pageable
    ) {
        return Response.ok(featureService.getFeaturesModule(moduleId, isInModule, pageable));
    }

    @PostMapping(value = {"/addFeatures"})
    public Response addFeature2Module(
            @RequestBody @NotEmpty List<ModuleAddFeatureData> listModuleAddFeatureData) {
        moduleService.addFeatures2Module(listModuleAddFeatureData);
        return Response.ok();
    }

    @PostMapping(value = {"/updateFeatures"})
    public Response updateFeatureInModule(
            @RequestBody @NotEmpty List<ModuleAddFeatureData> listModuleAddFeatureData) {
        moduleService.updateFeatureInModule(listModuleAddFeatureData);
        return Response.ok();
    }

}
