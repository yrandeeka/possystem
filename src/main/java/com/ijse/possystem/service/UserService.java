package com.ijse.possystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.User;

@Service
public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id,User user);
    Void deleteUserById(Long id);
}
