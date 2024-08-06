package com.group5.adminservice.service;

import com.group5.adminservice.dto.ProductDto;
import com.group5.adminservice.entity.Product;
import com.group5.adminservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = toEntity(productDto);
        product = productRepository.save(product);
        return toDto(product);
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        updateEntity(product, productDto);
        product = productRepository.save(product);
        return toDto(product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllProductsDto() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setImageId(product.getImageId());
        return productDto;
    }

    private Product toEntity(ProductDto productDto) {
        Product product = new Product();
//        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setImageId(productDto.getImageId());
        return product;
    }


    private void updateEntity(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setImageId(productDto.getImageId());
    }

    @Override
    public String index() {
        return "index";
    }
}
