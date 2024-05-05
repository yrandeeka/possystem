package com.ijse.possystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.Supplier;

@Service
public interface SupplierService {
    
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(Long id);
    Supplier createSupplier(Supplier supplier);
}
