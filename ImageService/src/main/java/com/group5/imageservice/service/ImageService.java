package com.group5.imageservice.service;

import com.group5.imageservice.entity.Image;
import com.group5.imageservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image getImageById(String imageId) throws IOException {
        Optional<Image> image = imageRepository.findById(imageId);
        return image.orElseThrow(() -> new IOException("Image not found"));
    }

    public String saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        image.setContentType(file.getContentType());
        image.setData(file.getBytes());

        Image savedImage = imageRepository.save(image);
        return savedImage.getId();
    }

    public void deleteImage(String imageId) throws IOException {
        if (!imageRepository.existsById(imageId)) {
            throw new IOException("Image not found");
        }
        imageRepository.deleteById(imageId);
    }
}