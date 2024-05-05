package com.ijse.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.possystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
}
