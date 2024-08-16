package com.group5.imageservice.service;

import com.group5.imageservice.entity.Image;
import com.group5.imageservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${image.storage.path}")
    private String imageStoragePath;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public byte[] loadImage(File imageFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            return fis.readAllBytes();
        }
    }

    public Image saveImageMetadata(Image image) {
        return imageRepository.save(image);
    }

    public Image getImageMetadata(String id) {
        return imageRepository.findById(id).orElse(null);
    }
}
