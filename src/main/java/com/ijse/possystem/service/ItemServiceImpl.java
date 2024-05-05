package com.ijse.possystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.Item;
import com.ijse.possystem.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    };
    
    @Override
    public Item getItemById(Long id){
        return itemRepository.findById(id).orElse(null);
    };
    
    @Override
    public Item createItem(Item item){
        return itemRepository.save(item);
    };
}
