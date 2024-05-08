package com.ijse.possystem.service;

import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.Cart;

@Service
public interface CartService{
    Cart getCartById(Long id);
    Cart createCart(Cart cart);
    Cart updateCart(Long id, Cart cart);
    void deleteCart(Long id);
}
