package com.ijse.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.possystem.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    
}
