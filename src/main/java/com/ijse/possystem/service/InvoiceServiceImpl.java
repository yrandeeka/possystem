package com.ijse.possystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.Invoice;
import com.ijse.possystem.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService{
        
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    };
    
    @Override
    public Invoice getInvoiceById(Long id){
        return invoiceRepository.findById(id).orElse(null);
    };
    
    @Override
    public Invoice createInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    };
}
