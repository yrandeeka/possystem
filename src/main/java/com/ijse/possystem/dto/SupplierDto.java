package com.ijse.possystem.dto;

import java.util.List;

import com.ijse.possystem.entity.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDto {
    private String name;
    private int contactNo;
    private String address;
    private String email;
    private List<Item> items;
}
