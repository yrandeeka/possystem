package com.ijse.possystem.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijse.possystem.dto.CartDto;
import com.ijse.possystem.dto.CartItemDto;
import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.entity.CartItem;
import com.ijse.possystem.entity.Item;
import com.ijse.possystem.entity.User;
import com.ijse.possystem.repository.CartItemRepository;
import com.ijse.possystem.repository.CartRepository;
import com.ijse.possystem.repository.ItemRepository;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemRepository itemRepository;
    
    @Override
    public Cart getCartByUser(Long userId){
        return cartRepository.getCartByUserId(userId);
    }

    // @Transactional
    // @Override
    // public Cart createCart(CartDto cartDto){
    //     try {
    //         Cart cart=new Cart();
    //         cart.setLast_modified(LocalDateTime.now());
    //         cart.setUser(cartDto.getUser());
            
    //         Cart createCart=cartRepository.save(cart);
    //         for (CartItemDto cartThing : cartDto.getAddItems()) {
    //             Item existItem=itemRepository.findById(cartThing.getId()).orElse(null);
    //             CartItem cartItem=new CartItem();
    //             cartItem.setCartQty(cartThing.getCartQty());
    //             cartItem.setStatus("cart on");
    //             cartItem.setItem(existItem);
    //             cartItem.setCart(createCart);
    //             cartItemRepository.save(cartItem);
    //         }
    //         return createCart;
    //     } catch (Exception e) {
    //         return null;
    //     }
    // }

    @Override
    public  Cart createCart(User user){
        Cart cart=new Cart();
        cart.setLast_modified(LocalDateTime.now());
        cart.setUser(user);
        cart.setStatus("active");
        return cartRepository.save(cart);
    };
    @Override
    public Cart updateCart(Long id, Cart cart){
        Cart existCart=cartRepository.findById(id).orElse(cart);
        if (existCart==null) {
            return null;
        } else {
            existCart.setLast_modified(cart.getLast_modified());
            return cartRepository.save(existCart);
        }
    };
    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    };
}
