package com.ecommerce.EcommerceBackend.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {
    private String username;
    private String password;
}
