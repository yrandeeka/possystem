package com.ijse.possystem.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ijse.possystem.dto.CartItemDto;
import com.ijse.possystem.entity.CartItem;
import com.ijse.possystem.service.CartItemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class CartItemController {
    
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/cartitem/{cartId}")
    public List<CartItem> getCartItemsByCartId(@PathVariable Long cartId) {
       return cartItemService.getCartItemsByCartId(cartId);
    }
    

    @PostMapping("/cartitem")
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItemDto cartItemDto) {
        System.out.println("cartItemDto.getCartQty()"+cartItemDto.getCartQty());
        CartItem createCartItem=cartItemService.createCartItem(cartItemDto);
        if (createCartItem==null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(createCartItem);
        }
    }

    @PutMapping("updatecartitem")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItemDto cartItemDto) {
        CartItem updateCartItem=cartItemService.updateCartItem(cartItemDto);

        if (updateCartItem==null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(updateCartItem);
        }
    }

    @PutMapping("removecartitem/{id}")
    public CartItem removeCartItem(@PathVariable Long id) {
        return cartItemService.removeCartItem(id);
    }
    
}
