package com.ijse.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ijse.possystem.dto.InvoiceDto;
import com.ijse.possystem.entity.Invoice;
import com.ijse.possystem.service.InvoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
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
        Invoice invoice=new Invoice();
        
        invoice.setCustomer(invoice.getCustomer());
        invoice.setItems(invoice.getItems());
        invoice.setIssuedDate(invoiceDto.getIssuedDate());
        invoice.setTotalItems(invoiceDto.getTotalItems());
        invoice.setTotalPrice(invoiceDto.getTotalPrice());
        invoice.setDiscount(invoiceDto.getDiscount());
        return invoiceService.createInvoice(invoice);
    }
    


    
    
}
