package com.ecommerce.EcommerceBackend.controller;

import com.ecommerce.EcommerceBackend.Dto.CartDto;
import com.ecommerce.EcommerceBackend.Dto.ProductDto;
import com.ecommerce.EcommerceBackend.model.User;
import com.ecommerce.EcommerceBackend.repository.UserRepository;
import com.ecommerce.EcommerceBackend.service.CartService;
import com.ecommerce.EcommerceBackend.view.ApiResponse;
import com.ecommerce.EcommerceBackend.view.ItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserRepository repository;
    @PostMapping("/addToCart")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody ItemRequest req){
        User user = repository.findById(1).orElseThrow(()-> new RuntimeException("User not found"));
        cartService.addTocart(req, user.getEmail());
        return new ResponseEntity<>(new ApiResponse(true), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CartDto> getCart(){
        User user = repository.findById(1).orElseThrow(()-> new RuntimeException("User not found"));
        CartDto dto = cartService.getCart(user);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ApiResponse> remoteItemFromCart(@PathVariable int id){
        User user = repository.findById(1).orElseThrow(()-> new RuntimeException("User not found"));
        cartService.removeItemFromCart(id,user);
        return new ResponseEntity<>(new ApiResponse(true), HttpStatus.OK);
    }
}
