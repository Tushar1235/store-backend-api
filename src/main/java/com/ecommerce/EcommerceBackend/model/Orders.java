package com.ecommerce.EcommerceBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private String orderStatus;
    private String paymentStatus;
    private Date OrderCreatedDate;
    private double orderAmount;
    private String billingAddress;

    @OneToOne
    private User user;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<OrderItems> itemsSet = new HashSet<>();

}
