package com.group5.imageservice.controller;


import com.group5.imageservice.entity.Image;
import com.group5.imageservice.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("images")
public class ImageController {

    @Value("${image.storage.path}")
    private String imageStoragePath;

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageId) {
        try {
            File imageFile = new File(imageStoragePath + File.separator + imageId);

            byte[] imageBytes = imageService.loadImage(imageFile);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageId = file.getOriginalFilename();
            File dest = new File(imageStoragePath + File.separator + imageId);
            file.transferTo(dest);

            Image image = new Image(imageId, file.getOriginalFilename(), file.getContentType(), file.getSize());
            imageService.saveImageMetadata(image);

            return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/metadata/{imageId}")
    public ResponseEntity<Image> getImageMetadata(@PathVariable String imageId) {
        Image image = imageService.getImageMetadata(imageId);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
