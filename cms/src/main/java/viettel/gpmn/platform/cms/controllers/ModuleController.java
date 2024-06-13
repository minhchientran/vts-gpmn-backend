package viettel.gpmn.platform.cms.controllers;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.data.modules.ModuleAddFeatureData;
import viettel.gpmn.platform.cms.data.modules.ModuleData;
import viettel.gpmn.platform.cms.data.modules.ModuleQuery;
import viettel.gpmn.platform.cms.entities.Modules;
import viettel.gpmn.platform.cms.services.FeatureService;
import viettel.gpmn.platform.cms.services.ModuleService;
import viettel.gpmn.platform.core.controllers.GenericSaveController;
import viettel.gpmn.platform.core.data.response.Response;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/modules"})
public class ModuleController extends GenericSaveController<Modules, ModuleData, ModuleService> {

    private FeatureService featureService;

    @GetMapping
    public Response getListModules(ModuleQuery moduleQuery, Pageable pageable) {
        return Response.ok(this.service.getListModules(moduleQuery, pageable));
    }

    @GetMapping(value = {"/all"})
    public Response getListAllModules() {
        return Response.ok(this.service.getListAllModules());
    }

    @GetMapping(value = {"/{id}"})
    public Response getModuleDetail(
            @PathVariable(value = "id") String moduleId
    ) {
        return Response.ok(this.service.getModuleDetail(moduleId));
    }

    @GetMapping(value = {"/features"})
    public Response getFeaturesInModule(
            @RequestParam @NotEmpty String moduleId,
            @RequestParam Boolean isInModule,
            FeatureQuery featureQuery,
            Pageable pageable
    ) {
        return Response.ok(featureService.getFeaturesModule(moduleId, isInModule, featureQuery, pageable));
    }

    @PostMapping(value = {"/features"})
    public Response addFeature2Module(
            @RequestBody @NotEmpty List<ModuleAddFeatureData> listModuleAddFeatureData) {
        this.service.addFeatures2Module(listModuleAddFeatureData);
        return Response.ok();
    }

    @PutMapping(value = {"/features"})
    public Response updateFeatureInModule(
            @RequestBody @NotEmpty List<ModuleAddFeatureData> listModuleAddFeatureData) {
        this.service.updateFeatureInModule(listModuleAddFeatureData);
        return Response.ok();
    }
}
