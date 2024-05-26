package com.ijse.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ijse.possystem.dto.InvoiceDto;
import com.ijse.possystem.entity.Invoice;
import com.ijse.possystem.service.InvoiceService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class InvoiceController {
    
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoices")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/invoice/{id}")
    public Invoice getInvoice(@RequestParam Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping("/invoice")
    public Invoice createInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.createInvoice(invoiceDto);
    }
    


    
    
}
