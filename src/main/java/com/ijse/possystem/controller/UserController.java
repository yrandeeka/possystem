package com.ijse.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.possystem.dto.UserDto;
import com.ijse.possystem.entity.User;
import com.ijse.possystem.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @PostMapping("/users")
    public User createUser(@RequestBody UserDto userDto) {
        User user=new User();
        user.setFirst_name(userDto.getFirstName());
        user.setLast_name(userDto.getLastName());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setContact_no(userDto.getContactNo());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userService.createUser(user);
    }
    
    @PutMapping("user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user=new User();
        user.setFirst_name(userDto.getFirstName());
        user.setLast_name(userDto.getLastName());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setContact_no(userDto.getContactNo());
        user.setUsername(userDto.getUsername());
        if (userDto.getPassword()!=null) {
            user.setPassword(userDto.getPassword());
        }
        User updateUser=userService.updateUser(id, user);
        if(updateUser==null){
            return ResponseEntity.status(404).build();
        }
        else{
            return ResponseEntity.status(200).body(updateUser);
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
