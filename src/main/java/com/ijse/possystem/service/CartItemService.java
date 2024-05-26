package com.ijse.possystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.possystem.dto.CartItemDto;
import com.ijse.possystem.entity.CartItem;

@Service
public interface CartItemService {
    List<CartItem> getCartItemsByCartId(Long cartId);
    CartItem getCartItemByCartIdItemId(Long cartId,Long itemId);
    CartItem createCartItem(CartItemDto cartItemDto);
    CartItem updateCartItem(CartItemDto cartItemDto);
    CartItem removeCartItem(Long id);
}
