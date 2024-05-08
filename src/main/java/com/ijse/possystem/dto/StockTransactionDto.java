package com.ijse.possystem.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockTransactionDto {
    private LocalDateTime transactionDate;
    private String transactionType;
    private String units;
    private Double quantity;
    private String remarks;
    private List<ItemDto> itemDtos;
}
