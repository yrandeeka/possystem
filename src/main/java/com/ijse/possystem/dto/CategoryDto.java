package com.ijse.possystem.dto;

import java.util.List;

import com.ijse.possystem.entity.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private String name;
    private List<Item> items;
}
