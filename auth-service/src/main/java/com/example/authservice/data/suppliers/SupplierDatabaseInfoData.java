package com.example.authservice.data.suppliers;

public record SupplierDatabaseInfoData (
        String url,
        String username,
        String password,
        String supplierId

) {}
