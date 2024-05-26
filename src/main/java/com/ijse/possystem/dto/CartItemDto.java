package com.ijse.possystem.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long itemId;
    private Double cartQty;
    private Long cartId;
}