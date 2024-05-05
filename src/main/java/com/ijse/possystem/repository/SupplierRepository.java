package com.ijse.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.possystem.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long>{
    
}
