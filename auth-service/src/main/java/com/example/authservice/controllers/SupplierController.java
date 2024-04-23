package com.example.authservice.controllers;

import com.example.authservice.data.suppliers.SupplierDatabaseInfoData;
import com.example.authservice.entities.SupplierDatabaseInfo;
import com.example.authservice.repositories.SupplierDatabaseInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.data.response.Response;

@RestController
@RequestMapping(value = {"/v1/suppliers"})
public class SupplierController {

    SupplierDatabaseInfoRepository supplierDatabaseInfoRepository;

    ModelMapper modelMapper;

    @Autowired
    public SupplierController(
            SupplierDatabaseInfoRepository supplierDatabaseInfoRepository
    ) {
        this.supplierDatabaseInfoRepository = supplierDatabaseInfoRepository;
    }
    @PostMapping("/database")
    Response createDatabase(@RequestBody SupplierDatabaseInfoData supplierDatabaseInfoData) {
        SupplierDatabaseInfo supplierDatabaseInfo = modelMapper.map(supplierDatabaseInfoData, SupplierDatabaseInfo.class);
        supplierDatabaseInfoRepository.save(supplierDatabaseInfo);
        return Response.ok();
    }
}
