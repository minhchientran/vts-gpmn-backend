package com.example.authservice.controllers;

import com.example.authservice.data.suppliers.SupplierDatabaseInfoData;
import com.example.authservice.entities.SupplierDatabaseInfo;
import com.example.authservice.repositories.SupplierDatabaseInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.viettel.core.data.response.Response;

@RestController
@RequestMapping(value = {"/v1/suppliers"})
public class SupplierController {

    SupplierDatabaseInfoRepository supplierDatabaseInfoRepository;

    ModelMapper modelMapper;

    public SupplierController(
            SupplierDatabaseInfoRepository supplierDatabaseInfoRepository,
            ModelMapper modelMapper
    ) {
        this.supplierDatabaseInfoRepository = supplierDatabaseInfoRepository;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/database")
    Response createDatabase(@RequestBody SupplierDatabaseInfoData supplierDatabaseInfoData) {
        SupplierDatabaseInfo supplierDatabaseInfo = modelMapper.map(supplierDatabaseInfoData, SupplierDatabaseInfo.class);
        supplierDatabaseInfoRepository.save(supplierDatabaseInfo);
        return Response.ok();
    }
}
