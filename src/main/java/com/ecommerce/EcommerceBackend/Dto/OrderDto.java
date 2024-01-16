package com.ecommerce.EcommerceBackend.Dto;

import com.ecommerce.EcommerceBackend.model.OrderItems;
import com.ecommerce.EcommerceBackend.model.User;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int orderId;
    private String orderStatus;
    private String paymentStatus;
    private Date OrderCreatedDate;
    private double orderAmount;
    private String billingAddress;
    private UserDto user;
    private Set<OrderItemsDto> itemsSet = new HashSet<>();
}
