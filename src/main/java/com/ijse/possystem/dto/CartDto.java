package com.ijse.possystem.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ijse.possystem.entity.Item;
import com.ijse.possystem.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
    private LocalDateTime lastModified;
    private User user;
    private List<Item> items;
}
