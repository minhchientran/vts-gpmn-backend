package viettel.gpmn.platform.cms.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.features.FeatureQuery;
import viettel.gpmn.platform.cms.data.modules.ModuleAddFeatureData;
import viettel.gpmn.platform.cms.data.modules.ModuleData;
import viettel.gpmn.platform.cms.data.modules.ModuleQuery;
import viettel.gpmn.platform.cms.services.FeatureService;
import viettel.gpmn.platform.cms.services.ModuleService;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.BaseData;
import viettel.gpmn.platform.core.data.response.Response;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/modules"})
public class ModuleController extends BaseController {

    private FeatureService featureService;
    private ModuleService moduleService;

    @Operation(summary = "Get page of modules")
    @GetMapping
    public Response moduleService(ModuleQuery moduleQuery, Pageable pageable) {
        return Response.ok(moduleService.getListModules(moduleQuery, pageable));
    }

    @Operation(summary = "Create or update modules")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Response saveModules(@RequestBody List<ModuleData> listModuleData) {
        moduleService.save(listModuleData);
        return Response.ok();
    }

    @Operation(summary = "Update status of modules")
    @PutMapping(value = {"/status"})
    public Response updateModuleStatus(@RequestBody List<BaseData> listModuleData) {
        moduleService.updateModuleStatus(listModuleData);
        return Response.ok();
    }

    @Operation(summary = "Get list of all modules")
    @GetMapping(value = {"/all"})
    public Response getListAllModules() {
        return Response.ok(moduleService.getListAllModules());
    }

    @Operation(summary = "Get detail of a module by module id")
    @GetMapping(value = {"/{moduleId}"})
    public Response getModuleDetail(
            @PathVariable(value = "moduleId") String moduleId
    ) {
        return Response.ok(moduleService.getModuleDetail(moduleId));
    }

    @Operation(summary = "Get page of feature in or not in a module by module id")
    @GetMapping(value = {"/features"})
    public Response getFeaturesInModule(
            @RequestParam @NotEmpty String moduleId,
            @RequestParam Boolean isInModule,
            FeatureQuery featureQuery,
            Pageable pageable
    ) {
        return Response.ok(featureService.getFeaturesModule(moduleId, isInModule, featureQuery, pageable));
    }

    @Operation(summary = "Add feature to a module")
    @PostMapping(value = {"/features"})
    public Response addFeature2Module(
            @RequestBody @NotEmpty List<ModuleAddFeatureData> listModuleAddFeatureData) {
        moduleService.addFeatures2Module(listModuleAddFeatureData);
        return Response.ok();
    }

    @Operation(summary = "Update feature in a module")
    @PutMapping(value = {"/features"})
    public Response updateFeatureInModule(
            @RequestBody @NotEmpty List<ModuleAddFeatureData> listModuleAddFeatureData) {
        moduleService.updateFeatureInModule(listModuleAddFeatureData);
        return Response.ok();
    }
}
