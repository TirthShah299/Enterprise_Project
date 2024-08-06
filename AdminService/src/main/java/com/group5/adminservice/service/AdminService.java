package com.group5.adminservice.service;

import com.group5.adminservice.dto.ProductDto;
import com.group5.adminservice.entity.Product;

import java.util.List;

public interface AdminService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(String id, ProductDto productDto);
    void deleteProduct(String id);
//    List<Product> getAllProducts();
    List<ProductDto> getAllProductsDto();
    String index();
}
