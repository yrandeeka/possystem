package com.ijse.possystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;
    private String units;
    private Double quantity;
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    // @ManyToMany
    // @JoinTable(
    //     name="item_supplier",
    //     joinColumns=@JoinColumn(name = "item_id"),
    //     inverseJoinColumns = @JoinColumn(name="supplier_id")
    // )
    // private List<Supplier> suppliers;
    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<StockTransaction> stockTransactions;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private List<Invoice> invoices;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private List<Cart> carts;
}
