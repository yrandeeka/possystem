package com.ijse.possystem.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijse.possystem.entity.Item;
import com.ijse.possystem.entity.StockTransaction;
import com.ijse.possystem.repository.ItemRepository;
import com.ijse.possystem.repository.StockTransactionRepository;

@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockTransactionRepository stockTransactionRepository;

    @Override
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    };
    
    @Override
    public Item getItemById(Long id){
        return itemRepository.findById(id).orElse(null);
    };

    @Transactional
    @Override
    public Item createItem(Item item,StockTransaction stockTransaction){
        
        try {
            Item createItem=itemRepository.save(item);
            stockTransaction.setItem(createItem);
            System.out.println("createItem"+createItem.getId());
            stockTransactionRepository.save(stockTransaction);
            return createItem;    
        } catch (Exception e) {
            return null;
        }
    };
}
