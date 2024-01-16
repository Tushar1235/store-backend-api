package com.ecommerce.EcommerceBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemsId;
    private int totalQuantity;
    private double totalPrice;
    @OneToOne
    private Product product;
    @ManyToOne
    private Orders orders;
}
