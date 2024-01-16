package com.ecommerce.EcommerceBackend.Dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String gender;
    private String address;
    private Date date;
}
