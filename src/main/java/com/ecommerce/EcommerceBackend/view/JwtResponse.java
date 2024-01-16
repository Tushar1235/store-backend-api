package com.ecommerce.EcommerceBackend.view;

import com.ecommerce.EcommerceBackend.Dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private  String token;
    private UserDto userDto;
}
