package com.group5.imageservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "images")
public class Image {

    @Id
    private String id;
    private String name;
    private String mimeType;
    private long size;

    // Constructors, getters, and setters
    public Image() {}

    public Image(String id, String name, String mimeType, long size) {
        this.id = id;
        this.name = name;
        this.mimeType = mimeType;
        this.size = size;
    }

}
