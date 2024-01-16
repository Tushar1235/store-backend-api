package com.ecommerce.EcommerceBackend.Dto;

import com.ecommerce.EcommerceBackend.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartItemDto {
    private  int cartItemId;
    private int quantity;
    private double totalPrice;
    @JsonIgnore
    private CartDto cartDto;
    private ProductDto productDto;
}
