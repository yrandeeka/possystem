package com.ijse.possystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.possystem.entity.CartItem;
import com.ijse.possystem.entity.StockTransaction;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction,Long>{

    //SELECT * FROM Record ORDER BY lastUpdated DESC LIMIT 1
    @Query("SELECT o FROM StockTransaction o ORDER BY o.transactionDate DESC LIMIT 1")
    Optional<StockTransaction> testFindTopByOrderByLastUpdatedDesc();

    // @Query("SELECT o FROM CartItem o WHERE o.cart.id= :id AND o.status='active'")
    // List<CartItem> getCartItemsByCartId(@Param("id") Long cartId);
}
