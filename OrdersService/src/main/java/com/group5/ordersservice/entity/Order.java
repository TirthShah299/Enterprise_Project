package com.group5.ordersservice.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "order_table")
@Data
@AllArgsConstructor

public class Order {

    @Id
    private String id;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;

//    @DBRef // Use DBRef to reference related documents
    private List<OrderItem> orderItems;
    public Order() {
        this.orderDate = LocalDateTime.now();
    }

}

