package com.ijse.possystem.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockTransaction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private String transactionType; 

    @Column(nullable = false)
    private String units;

    @Column(nullable = false)
    private Double totalPrice;
    
    @Column(nullable = false)
    private Double quantity;
    
    private String remarks;

    // @ManyToMany
    // @JoinTable(
    //     name = "stock_transaction_item",
    //     joinColumns=@JoinColumn(name = "stock_transaction_id"),
    //     inverseJoinColumns = @JoinColumn(name="item_id")
    // )
    // private List<Item> items;
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;
}
