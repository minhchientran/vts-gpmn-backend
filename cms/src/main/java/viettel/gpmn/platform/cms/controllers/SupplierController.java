package viettel.gpmn.platform.cms.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.suppliers.SupplierData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierModuleData;
import viettel.gpmn.platform.cms.data.suppliers.SupplierQuery;
import viettel.gpmn.platform.cms.entities.Suppliers;
import viettel.gpmn.platform.cms.services.SupplierService;
import viettel.gpmn.platform.core.controllers.GenericSaveController;
import viettel.gpmn.platform.core.data.response.Response;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/suppliers"})
public class SupplierController extends GenericSaveController<Suppliers, SupplierData, SupplierService> {

    @GetMapping
    public Response getListSuppliers(SupplierQuery supplierQuery, Pageable pageable) {
        return Response.ok(this.service.getListSuppliers(supplierQuery, pageable));
    }

    @GetMapping(value = {"/{supplierId}"})
    public Response getSupplierDetail(@PathVariable String supplierId) {
        return Response.ok(this.service.getSupplierDetail(supplierId));
    }

    @GetMapping(value = {"/modules"})
    public Response getListSupplierModules(@RequestParam String supplierId) {
        return Response.ok(this.service.getListSupplierModules(supplierId));
    }

    @PostMapping(value = {"/modules"})
    public Response createSupplierModules(@RequestBody List<SupplierModuleData> listSupplierModuleData) {
        this.service.createSupplierModules(listSupplierModuleData);
        return Response.ok();
    }

    @PutMapping(value = {"/modules"})
    public Response updateSupplierModules(@RequestBody List<SupplierModuleData> listSupplierModuleData) {
        this.service.updateSupplierModules(listSupplierModuleData);
        return Response.ok();
    }

}
