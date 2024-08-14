package com.group5.ordersservice.service;

import com.group5.ordersservice.entity.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class OrderPlacedEvent {
    private String orderId;
    private List<OrderItem> orderItems;

    public OrderPlacedEvent() {
        // Default constructor for deserialization
    }

    public OrderPlacedEvent(String orderId, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderPlacedEvent{" +
                "orderId='" + orderId + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPlacedEvent that = (OrderPlacedEvent) o;

        if (!orderId.equals(that.orderId)) return false;
        return orderItems.equals(that.orderItems);
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + orderItems.hashCode();
        return result;
    }
}
