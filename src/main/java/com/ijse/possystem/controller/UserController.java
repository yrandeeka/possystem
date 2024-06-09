package com.ijse.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.possystem.dto.UserDto;
import com.ijse.possystem.entity.User;
import com.ijse.possystem.security.WebSecurityConfig;
import com.ijse.possystem.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
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
        if (!userDto.getFirstName().isEmpty() || !userDto.getLastName().isEmpty() || userDto.getContactNo()!=0 || 
            !userDto.getUsername().isEmpty() || !userDto.getPassword().isEmpty()) {
            user.setFirst_name(userDto.getFirstName());
            user.setLast_name(userDto.getLastName());
            user.setAddress(userDto.getAddress());
            user.setEmail(userDto.getEmail());
            user.setContact_no(userDto.getContactNo());
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return userService.createUser(user);
        } else {
            return null;
        }
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
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
