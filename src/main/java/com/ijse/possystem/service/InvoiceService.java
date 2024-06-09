package com.ijse.possystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.possystem.dto.InvoiceDto;
import com.ijse.possystem.entity.Invoice;

@Service
public interface InvoiceService {
    
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(Long id);
    Invoice createInvoice(InvoiceDto invoiceDto);
    Invoice getLatestInvoice();
}
