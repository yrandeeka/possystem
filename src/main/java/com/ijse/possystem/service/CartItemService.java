package com.ijse.possystem.service;

import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.entity.CartItem;
import com.ijse.possystem.entity.Item;

@Service
public interface CartItemService {
    CartItem getCartItemById(Long id);
    CartItem createCartItem(Double qty,Cart cart,Item item);
}
