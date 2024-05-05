package com.ijse.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.ijse.possystem.entity.Item;
import com.ijse.possystem.service.ItemService;

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

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
    
    @GetMapping("/items/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.getItemById(id);
    }
    
    @PostMapping("/items")
    public Item createItems(@RequestBody Item item) {
        return itemService.createItem(item);
    }
    
}
