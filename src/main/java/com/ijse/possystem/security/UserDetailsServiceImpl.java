package com.ijse.possystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ijse.possystem.entity.User;
import com.ijse.possystem.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        User user=userRepository.findByUsername(username).orElse(null);

        if (user==null) {
            throw new UsernameNotFoundException("user is not found");
        }

        return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .build();
    }
}
