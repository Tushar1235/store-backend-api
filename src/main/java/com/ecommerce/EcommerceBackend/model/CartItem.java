package com.ecommerce.EcommerceBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int cartItemId;
    private int quantity;
    private double totalPrice;

    //Relationship with cart table.
    @JsonBackReference
    @ManyToOne
    private Cart cart;
    @OneToOne
    private Product product;
}
