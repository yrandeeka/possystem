package com.ijse.possystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.Customer;

@Service
public interface CustomerService {
    
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    
}
