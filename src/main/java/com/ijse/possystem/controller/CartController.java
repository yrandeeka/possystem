package com.ijse.possystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ijse.possystem.dto.CartDto;
import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.service.CartService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/{id}")
    public Cart getCartById(@PathVariable Long id){
        return cartService.getCartById(id);
    }

    @PostMapping("/carts")
    public Cart createCartById(@RequestBody Cart cart){

        return cartService.createCart(cart);
    }

    @PutMapping("carts/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody CartDto entity) {
        
        return null;
    }
}
