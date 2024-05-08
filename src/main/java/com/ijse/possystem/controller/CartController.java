package com.ijse.possystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ijse.possystem.dto.CartDto;
import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.service.CartService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<Cart> createCartById(@RequestBody CartDto cartDto){
        Cart cart=new Cart();
        cart.setLast_modified(cartDto.getLastModified());
        cart.setUser(cartDto.getUser());
        cart.setItems(cartDto.getItems());
        Cart createCart=cartService.createCart(cart);
        if (createCart==null) {
            return ResponseEntity.status(500).build();
        } else {
            return ResponseEntity.status(200).body(createCart);
        }
    }

    @PutMapping("carts/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody CartDto cartDto) {
        Cart cart=new Cart();
        cart.setLast_modified(cartDto.getLastModified());
        cart.setItems(cartDto.getItems());
        Cart updateCart=cartService.updateCart(id,cart);
        if(updateCart==null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(updateCart);
    }

    @DeleteMapping("cart/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
