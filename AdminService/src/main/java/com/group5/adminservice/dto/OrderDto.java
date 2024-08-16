package com.group5.adminservice.dto;

import lombok.Data;

@Data
    public class OrderDto {
        private String id;
        private String orderDate;
        private Double totalPrice;
    }