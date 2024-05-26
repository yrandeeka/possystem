package com.ijse.possystem.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ijse.possystem.entity.CartItem;
import com.ijse.possystem.entity.Customer;
import com.ijse.possystem.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDto{
    private int totalItems;
    private LocalDateTime issuedDate;
    private Double discountFraction;
    private Double totalPrice;
    private Double finalPrice;
    private User user;
    private Customer customer;
    private List<CartItem> cartItems;
}
