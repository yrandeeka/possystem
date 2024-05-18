package com.ijse.possystem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ijse.possystem.entity.Item;
import com.ijse.possystem.entity.StockTransaction;

@Service
public interface ItemService {
    List<Item> getAllItems();
    Item getItemById(Long id);
    Item createItem(Item item,StockTransaction stockTransaction);
    Item updateItem(Item item,StockTransaction stockTransaction);
    Item changeItemStatus(Long id,Item item);
}
