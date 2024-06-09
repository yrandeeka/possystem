package com.ijse.possystem.dto;

import com.ijse.possystem.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidUserDto {
    private String jwtToken;
    private User user;
    private boolean isAuthenticated;
}
