package com.ijse.possystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Override
    public Cart getCartById(Long id){
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart createCart(Cart cart){
        return cartRepository.save(null);
    }
    @Override
    public Cart updateCart(Long id, Cart cart){
        Cart existCart=cartRepository.findById(id).orElse(cart);
        if (existCart==null) {
            return null;
        } else {
            existCart.setLast_modified(cart.getLast_modified());
            existCart.setItems(cart.getItems());
            return cartRepository.save(existCart);
        }
    };
    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    };
}
