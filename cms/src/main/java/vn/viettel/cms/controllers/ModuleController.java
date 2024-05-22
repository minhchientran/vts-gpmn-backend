package vn.viettel.cms.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import vn.viettel.cms.data.features.FeatureAddControlData;
import vn.viettel.cms.data.modules.ModuleAddFeatureData;
import vn.viettel.cms.data.modules.ModuleData;
import vn.viettel.cms.data.modules.ModuleQuery;
import vn.viettel.cms.services.ModuleService;
import lombok.AllArgsConstructor;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/module"})
public class ModuleController extends BaseController {

    private final ModuleService moduleService;

    @GetMapping(value = {"/modules"})
    public Response getListModule(ModuleQuery moduleQuery, Pageable pageable) {
        return Response.ok(moduleService.getListModule(moduleQuery, pageable));
    }

    @PostMapping
    @PutMapping
    public Response createModule(ModuleData moduleData) {
        moduleService.createModule(moduleData);
        return Response.ok();
    }

    @PostMapping(value = {"/addFeatures"})
    public Response addFeature2Module(@RequestBody List<ModuleAddFeatureData> listModuleAddFeatureData) {
        moduleService.addFeatures2Module(listModuleAddFeatureData);
        return Response.ok();
    }

}
