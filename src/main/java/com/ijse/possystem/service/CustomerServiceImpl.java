package com.ijse.possystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.Customer;
import com.ijse.possystem.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    };
    
    @Override
    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElse(null);
    };
    
    @Override
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    };
}
