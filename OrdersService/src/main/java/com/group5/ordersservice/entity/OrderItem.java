package com.group5.ordersservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private String productId;
    private int quantity;
    private BigDecimal price;


//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

//    @DBRef
//    private Order order; // Use DBRef to reference related document

}

