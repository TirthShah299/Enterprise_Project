package com.group5.adminservice.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String orderDate;
    private Double totalPrice;
}