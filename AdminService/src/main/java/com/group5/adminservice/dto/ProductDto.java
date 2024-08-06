package com.group5.adminservice.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String id; // Use String for MongoDB ID
    private String name;
    private String description;
    private String price; // Ensure this matches the type you need
    private Integer stock;
    private String imageId;
}
