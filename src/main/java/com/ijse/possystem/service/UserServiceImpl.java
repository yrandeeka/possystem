package com.ijse.possystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.User;
import com.ijse.possystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    };
    @Override
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    };
    @Override
    public User createUser(User user){
        return userRepository.save(user);
    };

    public User findByUsername(String username){
        User existUser=userRepository.findByUsername(username).orElse(null);
        return existUser;
    };
    @Override
    public User updateUser(Long id,User user){
        User exitUser=userRepository.findById(id).orElse(null);

        if (exitUser==null) {
            return null;
        }
        exitUser.setFirst_name(user.getFirst_name());
        exitUser.setLast_name(user.getLast_name());
        exitUser.setAddress(user.getAddress());
        exitUser.setEmail(user.getEmail());
        exitUser.setContact_no(user.getContact_no());
        exitUser.setUsername(user.getUsername());
        if (user.getPassword()!=null) {
            user.setPassword(user.getPassword());
        }
        return userRepository.save(exitUser); 
    };

    @Override
    public Void deleteUserById(Long id){
        userRepository.deleteById(id);
        return null;
    };
}
