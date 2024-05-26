package com.ijse.possystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.InvoiceItem;

@Service
public interface InvoiceItemService {

    InvoiceItem getInvoiceItemsByInvoiceId(Long invoiceId);
    List<InvoiceItem> getAllInvoiceItems();
} 
