package com.group5.imageservice.controller;

import com.group5.imageservice.entity.Image;
import com.group5.imageservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String imageId) {
        try {
            Image image = imageService.getImageById(imageId);
            String contentType = image.getContentType();

            // Check if content type is null or empty
            if (contentType == null || contentType.isEmpty()) {
                contentType = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE; // Default content type
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType((MediaType) MimeTypeUtils.parseMimeType(contentType));

            return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Handle invalid MIME type
            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageId = imageService.saveImage(file);
            return new ResponseEntity<>(imageId, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable("id") String imageId) {
        try {
            imageService.deleteImage(imageId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}