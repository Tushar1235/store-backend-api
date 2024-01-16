package com.ecommerce.EcommerceBackend.Dto;

import com.ecommerce.EcommerceBackend.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDto {
    private int orderItemsId;
    private int totalQuantity;
    private double totalPrice;
    private ProductDto product;
    @JsonIgnore
    private OrderDto orderDto;
}
