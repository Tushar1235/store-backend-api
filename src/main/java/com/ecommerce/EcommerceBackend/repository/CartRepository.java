package com.ecommerce.EcommerceBackend.repository;

import com.ecommerce.EcommerceBackend.model.Cart;
import com.ecommerce.EcommerceBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByUser(User user);
}
