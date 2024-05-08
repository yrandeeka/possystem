package com.ijse.possystem.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDto {
    private String name;
    private long contactNo;
    private String address;
    private String email;
    private List<ItemDto> itemDtos;
}
