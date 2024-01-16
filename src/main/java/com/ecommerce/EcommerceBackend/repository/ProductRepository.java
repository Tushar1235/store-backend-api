package com.ecommerce.EcommerceBackend.repository;

import com.ecommerce.EcommerceBackend.model.Category;
import com.ecommerce.EcommerceBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(Category category);
}
