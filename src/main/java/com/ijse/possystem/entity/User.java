package com.ijse.possystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(unique = true,nullable = false)
    private int contact_no;
    
    private String address;
    
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Cart cart;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Invoice> invoices;
     
}
