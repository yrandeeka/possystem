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
            stockTransactionRepository.save(stockTransaction);
            return createItem;    
        } catch (Exception e) {
            return null;
        }
    };

    @Transactional
    @Override
    public Item updateItem(Item item,StockTransaction stockTransaction){
        try {
            Item existItem=itemRepository.findById(item.getId()).orElse(null);
            if (existItem==null) {
                return null;
            } else {
                existItem.setName(item.getName());
                if (existItem.getQuantity()!=item.getQuantity()) {
                    existItem.setQuantity(existItem.getQuantity()+item.getQuantity());
                    stockTransaction.setTransactionType("Adjustment");
                    stockTransaction.setQuantity(item.getQuantity());
                }else{
                    stockTransaction.setTransactionType("Details Update");
                    stockTransaction.setQuantity(0.0);
                }
                existItem.setCategory(item.getCategory());
                existItem.setUnits(item.getUnits());
                existItem.setSupplier(item.getSupplier());
                existItem.setUnitPrice(item.getUnitPrice());  
                stockTransaction.setItem(existItem);
                stockTransactionRepository.save(stockTransaction);
                return existItem;            
            }
        } catch (Exception e) {
            return null;
        }
    };
    
    @Override
    public Item changeItemStatus(Long id,Item item){
        Item existItem=itemRepository.findById(id).orElse(null);
        if (existItem==null) {
            return null;
        } else {
            existItem.setStatus(item.getStatus());
            itemRepository.save(existItem);
            return existItem;
        }
    };
}
