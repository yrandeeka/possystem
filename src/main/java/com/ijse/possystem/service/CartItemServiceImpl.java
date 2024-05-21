package com.ijse.possystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.entity.CartItem;
import com.ijse.possystem.entity.Item;
import com.ijse.possystem.repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem getCartItemById(Long id){
        return null;
    };
    
    @Override
    public CartItem createCartItem(Double qty,Cart cart,Item item){
        CartItem cartItem=new CartItem();
        cartItem.setCartQty(qty);
        cartItem.setItem(item);
        cartItem.setStatus("on");

        CartItem createCartItem=cartItemRepository.save(cartItem);

        return createCartItem;
    };

}
