package com.ecommerce.EcommerceBackend.repository;


import com.ecommerce.EcommerceBackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
