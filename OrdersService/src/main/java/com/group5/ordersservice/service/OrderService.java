package com.group5.ordersservice.service;

import com.group5.ordersservice.entity.Order;
import com.group5.ordersservice.entity.OrderItem;
import com.group5.ordersservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    private static final String TOPIC = "OrderPlacedEvent";

    @Autowired
    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Order createOrder(List<OrderItem> orderItems) {
        BigDecimal totalPrice = orderItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        // Publish the event to Kafka
        OrderPlacedEvent event = new OrderPlacedEvent(order.getId(), orderItems);
        kafkaTemplate.send(TOPIC, event);

        return order;
    }
}
