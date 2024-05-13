package com.ijse.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ijse.possystem.dto.SupplierDto;
import com.ijse.possystem.entity.Supplier;
import com.ijse.possystem.service.SupplierService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/suppliers/{id}")
    public Supplier getSupplier(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping("/suppliers")
    public ResponseEntity<Supplier> createSupplier(@RequestBody SupplierDto supplierDto) {
        Supplier supplier=new Supplier();

        supplier.setName(supplierDto.getName());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setEmail(supplierDto.getEmail());
        supplier.setContact_no(supplierDto.getContactNo());
        Supplier createSupplier=supplierService.createSupplier(supplier);
        if(createSupplier==null){
            return ResponseEntity.status(404).build();
        }
        else{
            return ResponseEntity.status(200).body(createSupplier);
        }
    }

    @PutMapping("supplier/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody SupplierDto supplierDto) {
        Supplier supplier=new Supplier();
        Supplier updateSupplier;
       
        //supplier.setItems(supplierDto.getItemDtos());
        if (!supplierDto.getName().isEmpty() || supplierDto.getContactNo()!=0) {
            supplier.setName(supplierDto.getName());
            supplier.setContact_no(supplierDto.getContactNo());
            supplier.setAddress(supplierDto.getAddress());
            supplier.setEmail(supplierDto.getEmail());
            updateSupplier= supplierService.updateCustomer(id,supplier);
        } else {
            updateSupplier=null;
        }
            
        if(updateSupplier==null){
            return ResponseEntity.status(404).build();
        }
        else{
            return ResponseEntity.status(200).body(updateSupplier);
        }
    }

    @DeleteMapping("supplier/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        supplierService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    
}
