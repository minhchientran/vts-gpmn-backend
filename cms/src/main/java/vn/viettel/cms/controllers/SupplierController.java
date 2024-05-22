package vn.viettel.cms.controllers;

import vn.viettel.cms.data.suppliers.SupplierData;
import vn.viettel.cms.data.suppliers.SupplierModuleData;
import vn.viettel.cms.services.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;

@AllArgsConstructor
@RestController
@RequestMapping(value = {"/v1/supplier"})
public class SupplierController extends BaseController {

    private final SupplierService supplierService;

    @PostMapping
    Response createSupplier(@RequestBody SupplierData supplierData) {
        supplierService.createSupplier(supplierData);
        return Response.ok();
    }

    @PostMapping(value = {"/module"})
    Response createSupplierModule(SupplierModuleData supplierModuleData) {
        supplierService.createSupplierModule(supplierModuleData);
        return Response.ok();
    }

}
