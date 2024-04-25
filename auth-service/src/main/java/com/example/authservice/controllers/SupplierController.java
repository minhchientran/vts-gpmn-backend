package com.example.authservice.controllers;

import com.example.authservice.data.suppliers.SupplierData;
import com.example.authservice.data.suppliers.SupplierModuleData;
import com.example.authservice.services.SupplierService;
import org.springframework.web.bind.annotation.*;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;

@RestController
@RequestMapping(value = {"/v1/supplier"})
public class SupplierController extends BaseController {

    private final SupplierService supplierService;

    public SupplierController(
            SupplierService supplierService
    ) {
        this.supplierService = supplierService;
    }
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
