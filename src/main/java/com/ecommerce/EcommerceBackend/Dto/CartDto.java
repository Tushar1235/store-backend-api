package com.ecommerce.EcommerceBackend.Dto;

import com.ecommerce.EcommerceBackend.model.CartItem;
import com.ecommerce.EcommerceBackend.model.User;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private int cartId;
    private Set<CartItem> cartItem = new HashSet<>();
    UserDto user;
}
