package com.ijse.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.possystem.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    
    @Query("SELECT o FROM Cart o WHERE o.user.id= :id")
    Cart getCartByUserId(@Param("id") Long id);
}
