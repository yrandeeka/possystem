package com.ijse.possystem.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ijse.possystem.dto.CartDto;
import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.entity.CartItem;
import com.ijse.possystem.entity.Item;
import com.ijse.possystem.entity.StockTransaction;
import com.ijse.possystem.service.CartItemService;
import com.ijse.possystem.service.CartService;
import com.ijse.possystem.service.ItemService;

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
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/cart/{id}")
    public Cart getCartById(@PathVariable Long id){
        return cartService.getCartById(id);
    }

    @PostMapping("/carts")
    public ResponseEntity<Cart> createCartById(@RequestBody CartDto cartDto){
        List<CartItem> cartItems=new ArrayList<>();
        //cartItem.set

        Cart cart=new Cart();
        cart.setLast_modified(LocalDateTime.now());
        cart.setUser(cartDto.getUser());
        Cart createCart=cartService.createCart(cart);

        for (CartItem cartThing : cartDto.getAddItems()) {
            Item existItem=itemService.getItemById(cartThing.getId());
            CartItem createCartItem=cartItemService.createCartItem(cartThing.getCartQty(),createCart, existItem);
            cartItems.add(createCartItem);
        }

        if (cartItems.size()!=cartDto.getAddItems().size()) {
            return ResponseEntity.status(500).build();
        } else {
            return ResponseEntity.status(200).body(createCart);
        }
    }

    @PutMapping("carts/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody CartDto cartDto) {
        Cart cart=new Cart();
        cart.setLast_modified(LocalDateTime.now());
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
