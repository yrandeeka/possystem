package com.ijse.possystem.dto;

import java.util.List;

import com.ijse.possystem.entity.Invoice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String firstName;
    private String lastName;
    private Long contactNo;
    private String address;
    private String email;
    private List<Invoice> invoices;
}
