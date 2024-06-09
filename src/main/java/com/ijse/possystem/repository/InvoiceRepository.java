package com.ijse.possystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ijse.possystem.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    
    //Optional<Invoice> findTopByOrderByLastUpdatedDesc();
    @Query("SELECT o FROM Invoice o ORDER BY o.issuedDate DESC LIMIT 1")
    Optional<Invoice> testFindTopByOrderByLastUpdatedDesc();
}
