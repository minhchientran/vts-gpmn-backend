package viettel.gpmn.platform.cms.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.suppliers.SupplierData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierModuleData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierQuery;
import viettel.gpmn.platform.cms.services.SupplierService;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.response.Response;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/suppliers"})
@Validated
public class SupplierController extends BaseController {
    
    private SupplierService getListSuppliers;

    @Operation(summary = "Get page of suppliers")
    @GetMapping
    public Response getListSuppliers(SupplierQuery supplierQuery, Pageable pageable) {
        return Response.ok(getListSuppliers.getListSuppliers(supplierQuery, pageable));
    }

    @Operation(summary = "Get info of a supplier")
    @GetMapping(value = {"/{supplierId}"})
    public Response getSupplierDetail(@PathVariable @NotBlank String supplierId) {
        return Response.ok(getListSuppliers.getSupplierDetail(supplierId));
    }

    @Operation(summary = "Create a supplier")
    @PostMapping(value = {"/supplier"})
    public Response createSupplier(@RequestBody SupplierData supplierData) {
        getListSuppliers.createSupplier(supplierData);
        return Response.ok();
    }

    @Operation(summary = "Update a supplier by supplier id")
    @PutMapping(value = {"/{supplierId}"})
    public Response updateSupplier(
            @PathVariable @NotBlank String supplierId,
            @RequestBody SupplierData supplierData) {
        getListSuppliers.updateSupplier(supplierId, supplierData);
        return Response.ok();
    }

    @Operation(summary = "Get page of modules in a supplier")
    @GetMapping(value = {"/modules"})
    public Response getListSupplierModules(
            @RequestParam String supplierId,
            Pageable pageable
    ) {
        return Response.ok(getListSuppliers.getListSupplierModules(supplierId, pageable));
    }

    @Operation(summary = "Add modules to a supplier")
    @PostMapping(value = {"/modules"})
    public Response createSupplierModules(@RequestBody List<SupplierModuleData> listSupplierModuleData) {
        getListSuppliers.createSupplierModules(listSupplierModuleData);
        return Response.ok();
    }

    @Operation(summary = "Update module in a supplier")
    @PutMapping(value = {"/modules"})
    public Response updateSupplierModules(@RequestBody List<SupplierModuleData> listSupplierModuleData) {
        getListSuppliers.updateSupplierModules(listSupplierModuleData);
        return Response.ok();
    }

}
