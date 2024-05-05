package com.ijse.possystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.StockTransaction;

@Service
public interface StockTransactionService {
    List<StockTransaction> getAllStockTransactions();
    StockTransaction getStockTransactionById(Long id);
    StockTransaction createStockTransaction(StockTransaction stockTransaction);
}
