package com.ijse.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ijse.possystem.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    
}
