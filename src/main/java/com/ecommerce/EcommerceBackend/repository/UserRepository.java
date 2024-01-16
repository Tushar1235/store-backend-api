package com.ecommerce.EcommerceBackend.repository;

import com.ecommerce.EcommerceBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
