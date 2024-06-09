package com.ijse.possystem.controller;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.possystem.dto.LoginDto;
import com.ijse.possystem.dto.ValidUserDto;
import com.ijse.possystem.entity.User;
import com.ijse.possystem.security.jwt.JwtUtils;
import com.ijse.possystem.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public ValidUserDto login(@RequestBody LoginDto loginDto) {
        try {
            ValidUserDto validUserDto=new ValidUserDto();
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername()
            ,loginDto.getPassword()));
    
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken=jwtUtils.generateJwtToken(authentication);

            if (jwtToken==null) {
                return  null;
            } else {
                User validUser=userService.findByUsername(loginDto.getUsername());

                validUserDto.setUser(validUser);
                validUserDto.setJwtToken(jwtToken);
                validUserDto.setAuthenticated(true);
            }
            return validUserDto;
        } catch (Exception e) {
            System.out.println("login dto - "+e);
            return null;
        }
    }
    
    
}
