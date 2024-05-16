package com.ijse.possystem.controller;

import java.time.LocalDateTime;
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

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@Log4j2
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
    
    @GetMapping("/items/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.getItemById(id);
    }
    
    @PostMapping("/items")
    public ResponseEntity<Item> createItems(@RequestBody ItemDto itemDto) {

        //System.out.println("itemDto-"+itemDto.getName());
        log.info("ItemDto {}", itemDto.getName());

        try {
            StockTransaction stockTransaction=new StockTransaction();
            stockTransaction.setTransactionDate(LocalDateTime.now());
            log.info("date-{}",stockTransaction.getTransactionDate());
            stockTransaction.setTransactionType("Purchase");
            stockTransaction.setUnits(itemDto.getUnits());
            stockTransaction.setQuantity(itemDto.getQuantity());
            stockTransaction.setRemarks(itemDto.getRemarks());

            Item item=new Item();
            item.setName(itemDto.getName());
            item.setQuantity(itemDto.getQuantity());
            item.setCategory(itemDto.getCategory());
            item.setUnits(itemDto.getUnits());
            item.setSupplier(itemDto.getSupplier());
            item.setUnitPrice(itemDto.getUnitPrice());
            
            Item createItem=itemService.createItem(item, stockTransaction);

            if (createItem==null) {
                return ResponseEntity.status(404).build();
            } else {
                return ResponseEntity.status(200).body(createItem);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
            return ResponseEntity.status(500).build();
        }
        
    }
    
}
