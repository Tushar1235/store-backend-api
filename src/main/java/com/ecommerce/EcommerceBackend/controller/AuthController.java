package com.ecommerce.EcommerceBackend.controller;

import com.ecommerce.EcommerceBackend.Dto.JwtRequest;
import com.ecommerce.EcommerceBackend.Dto.UserDto;
import com.ecommerce.EcommerceBackend.config.security.JwtHelper;
import com.ecommerce.EcommerceBackend.view.JwtResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtHelper helper;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest request){
        authentcateUser(request.getUsername(),request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = helper.generateToken(userDetails);
        return  new JwtResponse(token,modelMapper.map(userDetails, UserDto.class));
    }
    private void authentcateUser(String username, String password){
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (BadCredentialsException e){
            throw  new RuntimeException("invalid username or password");
        }
        catch (DisabledException e){
            throw  new RuntimeException("User not found");
        }
    }
}
