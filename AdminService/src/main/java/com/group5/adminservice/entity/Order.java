package com.group5.adminservice.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "orders")
@Data
public class Order {
    @Id
    private Long id;
    private LocalDateTime orderDate;
    private Double totalPrice;
}