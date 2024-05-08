package com.ijse.possystem.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private String name;
    private String units;
    private Double quantity;
    private Double price;
    private CategoryDto categoryDto;
    private List<SupplierDto> supplierDtos;
    private List<StockTransactionDto> stockTranscactionDtos;
    private List<InvoiceDto> invoiceDtos;
    private List<CartDto> cartDtos;
}
