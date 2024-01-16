package com.ecommerce.EcommerceBackend.controller;

import com.ecommerce.EcommerceBackend.Dto.OrderDto;
import com.ecommerce.EcommerceBackend.model.User;
import com.ecommerce.EcommerceBackend.repository.UserRepository;
import com.ecommerce.EcommerceBackend.service.OrderService;
import com.ecommerce.EcommerceBackend.view.ApiResponse;
import com.ecommerce.EcommerceBackend.view.ItemRequest;
import com.ecommerce.EcommerceBackend.view.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderService service;
    @PostMapping(value = "/submitOrder", produces = "application/json")
    public ResponseEntity<OrderDto> submitOrder(@RequestBody OrderRequest request){
        User user = userRepository.findById(1).orElseThrow(()-> new RuntimeException("User not found"));
        OrderDto dto = service.submitOrder(request,user);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<ApiResponse> addToCart(@PathVariable int orderId){
        User user = userRepository.findById(1).orElseThrow(()-> new RuntimeException("User not found"));
        service.cancelOrder(orderId);
        return new ResponseEntity<>(new ApiResponse(true), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<OrderDto> getOrders(){
        User user = userRepository.findById(1).orElseThrow(()-> new RuntimeException("User not found"));
        OrderDto dto = service.getOrders(user);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
