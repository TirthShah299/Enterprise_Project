package com.group5.productservice.controller;

import com.group5.productservice.entity.Product;
import com.group5.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Fetch ALl Products
    @GetMapping("list")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Fetch Product by Id
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productRepository.findById(id).orElseThrow();
    }

    // Add New Product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Delete Product by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }

//    // Update Product
//    @PutMapping("/{id}")
//    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
//        Product existingProduct = productRepository.findById(id).orElseThrow();
//        existingProduct.setName(product.getName());
//        existingProduct.setDescription(product.getDescription());
//        existingProduct.setPrice(product.getPrice());
//        existingProduct.setStock(product.getStock());
//        existingProduct.setImageId(product.getImageId());
//        return productRepository.save(existingProduct);
//    }

}