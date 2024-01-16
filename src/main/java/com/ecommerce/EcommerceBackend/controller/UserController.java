package com.ecommerce.EcommerceBackend.controller;

import com.ecommerce.EcommerceBackend.Dto.UserDto;
import com.ecommerce.EcommerceBackend.service.UserService;
import com.ecommerce.EcommerceBackend.view.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDto userDto){
      userService.createUser(userDto);
      return  new ResponseEntity<>(new ApiResponse(true), HttpStatus.CREATED);
    }

    @GetMapping("/viewall")
    public ResponseEntity<List<UserDto>> getALlUsers(){
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int userId){
        return  new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable int userId, @RequestParam UserDto userDto){
        userService.updateUser(userId,userDto);
        return  new ResponseEntity<>(new ApiResponse(true), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return  new ResponseEntity<>(new ApiResponse(true), HttpStatus.OK);
    }

}
