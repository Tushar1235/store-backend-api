package com.ecommerce.EcommerceBackend.Dto;

import com.ecommerce.EcommerceBackend.model.Category;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int product_id;
    private String product_name;
    private String productDescription;
    private double price;
    private int quantity;
    private boolean stock;
    private String imageUrl;
    private int categoryId;
}
