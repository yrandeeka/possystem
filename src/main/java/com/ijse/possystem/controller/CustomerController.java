package com.ijse.possystem.controller;

import java.util.List;

import com.ijse.possystem.dto.CustomerDto;
import com.ijse.possystem.entity.Customer;
import com.ijse.possystem.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
    
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer=new Customer();
        customer.setFirst_name(customerDto.getFirstName());
        customer.setLast_name(customerDto.getLastName());
        customer.setAddress(customerDto.getAddress());
        customer.setContact_no(customerDto.getContactNo());
        customer.setEmail(customerDto.getEmail());
        Customer createCustomer=customerService.createCustomer(customer);
        if (createCustomer==null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(createCustomer);
        }
    }

    @PutMapping("customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        Customer customer=new Customer();
        customer.setFirst_name(customerDto.getFirstName());
        customer.setLast_name(customerDto.getLastName());
        customer.setContact_no(customerDto.getContactNo());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setInvoices(customerDto.getInvoices());

        Customer updateCustomer= customerService.updateCustomer(id,customer);
        if(updateCustomer==null){
            return ResponseEntity.status(404).build();
        }
        else{
            return ResponseEntity.status(200).body(updateCustomer);
        }
    }

    @DeleteMapping("customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    
}
