package com.ijse.possystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijse.possystem.dto.CartItemDto;
import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.entity.CartItem;
import com.ijse.possystem.entity.Item;
import com.ijse.possystem.repository.CartItemRepository;
import com.ijse.possystem.repository.CartRepository;
import com.ijse.possystem.repository.ItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;
    

    @Override
    public List<CartItem> getCartItemsByCartId(Long cartId){
        return cartItemRepository.getCartItemsByCartId(cartId);
    };
    
    @Override
    public CartItem createCartItem(CartItemDto cartItemDto){
        Cart existCart=cartRepository.findById(cartItemDto.getCartId()).orElse(null);
        Item existItem=itemRepository.findById(cartItemDto.getItemId()).orElse(null);

        if (existCart==null || existItem==null) {
            return null;
        }
        else{
            CartItem existCartItem=getCartItemByCartIdItemId(existCart.getId(),existItem.getId());
            if (existCartItem!=null) {
                existCart.setLast_modified(LocalDateTime.now());
                existCartItem.setCartQty(cartItemDto.getCartQty());
                existCartItem.setStatus("active");
                cartRepository.save(existCart);
                CartItem updateCartItem=cartItemRepository.save(existCartItem);
                return updateCartItem;
            } else {
                CartItem cartItem=new CartItem();
                cartItem.setCart(existCart);
                cartItem.setItem(existItem);
                cartItem.setCartQty(cartItemDto.getCartQty());
                cartItem.setStatus("active");
                CartItem createCartItem=cartItemRepository.save(cartItem);
                return createCartItem;
            }
        }
    };

    @Transactional
    @Override
    public CartItem updateCartItem(CartItemDto cartItemDto){
        Cart existCart=cartRepository.findById(cartItemDto.getCartId()).orElse(null);
        Item existItem=itemRepository.findById(cartItemDto.getItemId()).orElse(null);
        if (existCart==null || existItem==null) {
            return null;
        }
        else{
            CartItem existCartItem=getCartItemByCartIdItemId(existCart.getId(),existItem.getId());
            if (existCartItem==null) {
                return null;
            }
            else{
                existCart.setLast_modified(LocalDateTime.now());
                existCartItem.setCartQty(cartItemDto.getCartQty());
                existCartItem.setStatus("active");
                cartRepository.save(existCart);
                CartItem updateCartItem=cartItemRepository.save(existCartItem);
                return updateCartItem;
            }
        }
    };

    @Override
    public CartItem getCartItemByCartIdItemId(Long cartId, Long itemId){
        return cartItemRepository.getCartItemByCartIdItemId(cartId,itemId);
    }

    @Override
    public CartItem removeCartItem(Long id){
        CartItem existCartItem=cartItemRepository.findById(id).orElse(null);

        if (existCartItem==null) {
            return null;
        } else {
            existCartItem.setStatus("deActive");
            cartItemRepository.save(existCartItem);
            return existCartItem;
        }
    };

}
