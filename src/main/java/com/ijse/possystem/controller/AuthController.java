package com.ijse.possystem.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.possystem.dto.LoginDto;
import com.ijse.possystem.security.jwt.JwtUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/auth/login")
    public String login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername()
            ,loginDto.getPassword()));
    
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken=jwtUtils.generateJwtToken(authentication);
    
            return jwtToken;
        } catch (Exception e) {
            System.out.println("login dto - "+e);
            return null;
        }

    }
    
    
}
