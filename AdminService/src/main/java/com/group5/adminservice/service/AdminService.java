package com.group5.adminservice.service;

import com.group5.adminservice.dto.OrderDto;
import com.group5.adminservice.dto.ProductDto;
import com.group5.adminservice.entity.Product;

import java.util.List;

public interface AdminService {
    // Products method
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(String id, ProductDto productDto);
    void deleteProduct(String id);
    List<ProductDto> getAllProductsDto();




    // Order Methods
    OrderDto createOrder(OrderDto orderDto);
    OrderDto updateOrder(String id, OrderDto orderDto);
    void deleteOrder(String id);
    List<OrderDto> getAllOrdersDto();

    String index();
}
