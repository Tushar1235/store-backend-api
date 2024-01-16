package com.ecommerce.EcommerceBackend.service;

import com.ecommerce.EcommerceBackend.Dto.OrderDto;
import com.ecommerce.EcommerceBackend.model.*;
import com.ecommerce.EcommerceBackend.repository.CartRepository;
import com.ecommerce.EcommerceBackend.repository.OrderRepository;
import com.ecommerce.EcommerceBackend.view.OrderRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ModelMapper mapper;
    public OrderDto submitOrder(OrderRequest request, User user){
        int cartId = request.getCartId();
        String address = request.getOrderAddress();
        Orders order = new Orders();
        AtomicReference<Double> totalAmount = new AtomicReference<>(0.0);
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new RuntimeException("there are no carts with this id"));
        Set<CartItem> cartItems = cart.getCartItem();
        Set<OrderItems> orderItems = cartItems.stream().map(cartItem -> {
            OrderItems orderItem = new OrderItems();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItem.setTotalQuantity(cartItem.getQuantity());
            totalAmount.set(totalAmount.get() + orderItem.getTotalPrice());
            orderItem.setOrders(order);
            return  orderItem;
        }).collect(Collectors.toSet());

        order.setOrderAmount(totalAmount.get());
        order.setOrderStatus("CREATED");
        order.setPaymentStatus("NOT PAYED");
        order.setBillingAddress(address);
        order.setOrderCreatedDate(new Date());
        order.setItemsSet(orderItems);
        order.setUser(user);
        Orders savedOrder;
        if(order.getOrderAmount() > 0){
           savedOrder = orderRepository.save(order);
            cart.getCartItem().clear();
            cartRepository.save(cart);
        }
        else {
            System.out.println("Cart is empty");
            return null;
        }
        return this.mapper.map(savedOrder,OrderDto.class);
    }

    public void cancelOrder(int orderId) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
        orders.setOrderStatus("CANCELLED");
        orderRepository.save(orders);
    }

    public OrderDto getOrders(User user){
        Orders orders = orderRepository.findByUser(user);
        return this.mapper.map(orders,OrderDto.class);
    }
}
