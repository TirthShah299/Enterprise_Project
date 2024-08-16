package com.group5.imageservice.repository;


import com.group5.imageservice.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
    // Additional query methods can be defined here if needed
}
