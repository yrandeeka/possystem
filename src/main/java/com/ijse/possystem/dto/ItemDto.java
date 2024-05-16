package com.ijse.possystem.dto;

import java.util.List;

import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.entity.Category;
import com.ijse.possystem.entity.Invoice;
import com.ijse.possystem.entity.StockTransaction;
import com.ijse.possystem.entity.Supplier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private String name;
    private String units;
    private Double quantity;
    private Double unitPrice;
    private String remarks;
    private Category category;
    private Supplier supplier;
    private List<StockTransaction> stockTranscactions;
    private List<Invoice> invoices;
    private List<Cart> carts;

}
