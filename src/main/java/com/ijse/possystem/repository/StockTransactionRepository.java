package com.ijse.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.possystem.entity.StockTransaction;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction,Long>{
    
}
