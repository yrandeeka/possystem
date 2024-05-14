package com.ijse.possystem.dto;

import java.util.List;

import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.entity.Invoice;
import com.ijse.possystem.entity.StockTransaction;

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
    private CategoryDto categoryDto;
    private SupplierDto supplierDto;
    private List<StockTransaction> stockTranscactions;
    private List<Invoice> invoices;
    private List<Cart> carts;
}
