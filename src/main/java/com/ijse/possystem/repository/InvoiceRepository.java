package com.ijse.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.possystem.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    
}
