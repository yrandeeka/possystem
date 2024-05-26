package com.ijse.possystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ijse.possystem.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT o FROM CartItem o WHERE o.cart.id= :id AND o.status='active'")
    List<CartItem> getCartItemsByCartId(@Param("id") Long cartId);

    @Query("SELECT o FROM CartItem o WHERE o.cart.id= :cartId AND o.item.id=:itemId")
    CartItem getCartItemByCartIdItemId(@Param("cartId") Long cartId,@Param("itemId") Long itemId);

    // @Query("SELECT o FROM CartItem o WHERE o.cart.id= :cartId AND o.id=cartItemId")
    // List<CartItem> getCartItemByCartIdCartItemId(@Param("id") Long cartId,@Param("id") Long cartItemId);
}
