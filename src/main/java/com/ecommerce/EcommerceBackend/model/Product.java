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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int product_id;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "product_description")
    private String productDescription;
    private double price;
    private int quantity;
    private boolean stock;
    @Column(name = "product_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
