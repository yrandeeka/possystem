package com.ijse.possystem.service;

import org.springframework.stereotype.Service;
import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.entity.User;

@Service
public interface CartService{
    Cart getCartByUser(Long userId);
    Cart createCart(User user);
    Cart updateCart(Long id, Cart cart);
    void deleteCart(Long id);
}
