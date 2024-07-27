package com.group5.ordersservice.controller;

import com.group5.ordersservice.entity.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<OrderItem> items;
}
