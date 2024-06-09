package com.ijse.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ijse.possystem.dto.StockTransactionDto;
import com.ijse.possystem.entity.StockTransaction;
import com.ijse.possystem.service.StockTransactionService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin(origins = "*")
public class StockTransactionController {
    
    @Autowired
    private StockTransactionService stockTransactionService;


    @GetMapping("/stocktransactions")
    public List<StockTransaction> getAllStockTransactions() {
        return stockTransactionService.getAllStockTransactions();
    }

    @GetMapping("/stocktransaction/{id}")
    public StockTransaction getStockTransaction(@PathVariable Long id) {
        return stockTransactionService.getStockTransactionById(id);
    }

    @GetMapping("/latest_stock_transaction")
    public StockTransaction getLatestStockTransaction() {
        return stockTransactionService.getLatestStockTransaction();
    }
        
}
