package com.ijse.possystem.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDto {
    private int totalItems;
    private LocalDateTime issuedDate;
    private Double discount;
    private Double totalPrice;
    private CustomerDto customerDto;
    private List<ItemDto> itemDtos;
}
