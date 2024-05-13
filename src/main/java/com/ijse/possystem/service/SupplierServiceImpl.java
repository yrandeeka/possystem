package com.ijse.possystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.Supplier;
import com.ijse.possystem.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService{
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    };
    
    @Override
    public Supplier getSupplierById(Long id){
        return supplierRepository.findById(id).orElse(null);
    };
    
    @Override
    public Supplier createSupplier(Supplier supplier){
        return supplierRepository.save(supplier);
    };
    @Override
    public Supplier updateCustomer(Long id, Supplier supplier){
        Supplier existSupplier=supplierRepository.findById(id).orElse(null);
        System.out.println(existSupplier.getContact_no());
        if (existSupplier!=null) {
            existSupplier.setName(supplier.getName());
            existSupplier.setContact_no(supplier.getContact_no());
            existSupplier.setAddress(supplier.getAddress());
            existSupplier.setEmail(supplier.getEmail());
            existSupplier.setItems(supplier.getItems());
            return supplierRepository.save(existSupplier);
        }
        else{
            return null;
        }
    };
    public void deleteCustomer(Long id){
        supplierRepository.deleteById(id);
    };
}
