package com.example.authservice.controllers;

import com.example.authservice.data.modules.ModuleData;
import com.example.authservice.services.ModuleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;

@RestController
@RequestMapping(value = {"/v1/module"})
public class ModuleController extends BaseController {

    private final ModuleService moduleService;

    public ModuleController(
            ModuleService moduleService
    ) {
        this.moduleService = moduleService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority()")
    public Response createModule(ModuleData moduleData) {
        moduleService.createModule(moduleData);
        return Response.ok();
    }
}
