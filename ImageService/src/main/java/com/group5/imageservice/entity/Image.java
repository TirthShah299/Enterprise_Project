package com.group5.imageservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "images")
public class Image {
    @Id
    private String id;
    private String name;
    private String contentType;
    private byte[] data; // Store the image data as a binary field
}