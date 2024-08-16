package com.group5.adminservice.service;

import com.group5.adminservice.dto.ProductDto;
import com.group5.adminservice.dto.OrderDto;
import com.group5.adminservice.entity.Product;
import com.group5.adminservice.entity.Order;
import com.group5.adminservice.repository.ProductRepository;
import com.group5.adminservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    // Product Methods
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = toProductEntity(productDto);
        product = productRepository.save(product);
        return toProductDto(product);
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        updateProductEntity(product, productDto);
        product = productRepository.save(product);
        return toProductDto(product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllProductsDto() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }

    // Order Methods
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = toOrderEntity(orderDto);
        order = orderRepository.save(order);
        return toOrderDto(order);
    }

    @Override
    public OrderDto updateOrder(String id, OrderDto orderDto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        updateOrderEntity(order, orderDto);
        order = orderRepository.save(order);
        return toOrderDto(order);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> getAllOrdersDto() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::toOrderDto)
                .collect(Collectors.toList());
    }

    // Conversion Methods for Product
    private ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setImageId(product.getImageId());
        return productDto;
    }

    private Product toProductEntity(ProductDto productDto) {
        Product product = new Product();
//        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setImageId(productDto.getImageId());
        return product;
    }

    private void updateProductEntity(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setImageId(productDto.getImageId());
    }

    // Conversion Methods for Order
    private OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderDate(String.valueOf(order.getOrderDate()));
        orderDto.setTotalPrice(order.getTotalPrice());
        return orderDto;
    }

    private Order toOrderEntity(OrderDto orderDto) {
        Order order = new Order();
//        order.setId(orderDto.getId());
        order.setOrderDate(LocalDateTime.parse(orderDto.getOrderDate()));
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }

    private void updateOrderEntity(Order order, OrderDto orderDto) {
        order.setOrderDate(LocalDateTime.parse(orderDto.getOrderDate()));
        order.setTotalPrice(orderDto.getTotalPrice());
    }

    @Override
    public String index() {
        return "index";
    }
}
