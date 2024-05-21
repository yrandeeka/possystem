package com.ijse.possystem.dto;
import java.util.List;

import com.ijse.possystem.entity.CartItem;
import com.ijse.possystem.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
    private User user;
    private List<CartItem> addItems;
}
