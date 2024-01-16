package com.ecommerce.EcommerceBackend.repository;

import com.ecommerce.EcommerceBackend.model.Orders;
import com.ecommerce.EcommerceBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    Orders findByUser(User user);

}
