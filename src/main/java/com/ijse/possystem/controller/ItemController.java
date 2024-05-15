package com.ijse.possystem.controller;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.ijse.possystem.dto.ItemDto;
import com.ijse.possystem.entity.Item;
import com.ijse.possystem.entity.StockTransaction;
import com.ijse.possystem.service.ItemService;
import com.ijse.possystem.service.StockTransactionService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @Autowired
    private StockTransactionService stockTransactionService;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
    
    @GetMapping("/items/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.getItemById(id);
    }
    
    
    @Transactional
    @PostMapping("/items")
    public ResponseEntity<Item> createItems(@RequestBody ItemDto itemDto) {
        System.out.println("supplierDto"+itemDto.getSupplierDto());
        try {
            StockTransaction stockTransaction=new StockTransaction();

            Date date=new Date();
            stockTransaction.setTransactionDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            System.out.println("date-"+stockTransaction.getTransactionDate());
            stockTransaction.setTransactionType("Purchase");
            stockTransaction.setUnits(itemDto.getUnits());
            stockTransaction.setQuantity(itemDto.getQuantity());
            stockTransaction.setRemarks(itemDto.getRemarks());

            Item item=new Item();
            item.setName(itemDto.getName());
            item.setQuantity(itemDto.getQuantity());
            item.setCategory(item.getCategory());
            item.setUnits(itemDto.getUnits());
            item.setSupplier(item.getSupplier());
            item.setUnitPrice(item.getUnitPrice());
            
            Item createItem=itemService.createItem(item, stockTransaction);

            if (createItem==null) {
                return ResponseEntity.status(404).build();
            } else {
                return ResponseEntity.status(200).body(createItem);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
        
    }
    
}
