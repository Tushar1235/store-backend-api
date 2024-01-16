package com.ecommerce.EcommerceBackend.service;

import com.ecommerce.EcommerceBackend.Dto.UserDto;
import com.ecommerce.EcommerceBackend.model.User;
import com.ecommerce.EcommerceBackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    PasswordEncoder encoder;

    public void createUser(UserDto userDto){
        userDto.setDate(new Date());
        User user = this.modelMapper.map(userDto,User.class);
        user.setPassword(encoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    public UserDto getUserById(int userId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        return  this.modelMapper.map(user,UserDto.class);
    }

    public List<UserDto> getUsers(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> {
            UserDto userDto =this.modelMapper.map(user, UserDto.class);
            userDto.setDate(user.getDate());
            return userDto;
        }).collect(Collectors.toList());
        return  userDtos;
    }

    public void updateUser(int userId, UserDto userDto){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
        user.setAddress(userDto.getAddress());
        userRepository.save(user);
    }

    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
