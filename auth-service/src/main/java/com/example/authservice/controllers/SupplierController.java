package com.example.authservice.controllers;

import com.example.authservice.data.suppliers.SupplierCreateData;
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
    Response createSupplier(@RequestBody SupplierCreateData supplierCreateData) {
        supplierService.createSupplier(supplierCreateData);
        return Response.ok();
    }
}
