package com.group5.ordersservice.controller;

import com.group5.ordersservice.entity.Order;
import com.group5.ordersservice.entity.OrderItem;
import com.group5.ordersservice.repository.OrderRepository;
import com.group5.ordersservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    // Add New Order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody List<OrderItem> orderItems) {
        Order order = orderService.createOrder(orderItems);
        return ResponseEntity.ok(order);
    }

    @GetMapping("list")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}

