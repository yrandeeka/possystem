package com.ijse.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.possystem.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
    
}
