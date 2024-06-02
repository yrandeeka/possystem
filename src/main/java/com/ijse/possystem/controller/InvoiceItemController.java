package com.ijse.possystem.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
public class InvoiceItemController {
    
    @GetMapping("/invoiceItems/{id}")
    public String getInvoiceItemsByInvoiceId(@PathVariable Long id) {
        return new String();
    }
    
}
