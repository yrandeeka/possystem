package com.ijse.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.possystem.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
    
}
