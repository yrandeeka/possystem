package com.ijse.possystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.StockTransaction;
import com.ijse.possystem.repository.StockTransactionRepository;

@Service
public class StockTransactionServiceImpl implements StockTransactionService {
        
    @Autowired
    private StockTransactionRepository stockTransactionRepository;

    @Override
    public List<StockTransaction> getAllStockTransactions(){
        return stockTransactionRepository.findAll();
    };
    
    @Override
    public StockTransaction getStockTransactionById(Long id){
        return stockTransactionRepository.findById(id).orElse(null);
    };
    
    @Override
    public StockTransaction createStockTransaction(StockTransaction stockTransaction){
        return stockTransactionRepository.save(stockTransaction);
    };

    @Override
    public StockTransaction getLatestStockTransaction(){
        StockTransaction lat_stockTransaction=stockTransactionRepository
                    .testFindTopByOrderByLastUpdatedDesc().orElse(null);
        return lat_stockTransaction;
    };
}
