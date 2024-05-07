package com.ijse.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ijse.possystem.dto.CategoryDto;
import com.ijse.possystem.entity.Category;
import com.ijse.possystem.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getMethodName(@PathVariable Long id) {
        Category category= categoryService.getCategoryById(id);
        if(category==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(category);
    }
    
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setItems(categoryDto.getItems());

        Category updateCategory=categoryService.updateCategory(id,category);
        if (updateCategory==null) {
            return ResponseEntity.status(404).build();
        } 
        return ResponseEntity.status(200).body(updateCategory);
    }
    
    @DeleteMapping("category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
