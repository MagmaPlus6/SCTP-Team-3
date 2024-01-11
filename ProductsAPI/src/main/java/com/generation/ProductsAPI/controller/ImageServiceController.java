package com.generation.ProductsAPI.controller;

import com.generation.ProductsAPI.exception.ResourceNotFoundException;
import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin
@RequestMapping("/api/image")
public class ImageServiceController {

    ImageService imageService;

    public ImageServiceController(@Autowired ImageService imageService){
        this.imageService = imageService;
    }

    @PostMapping()
    public ResponseEntity<String> uploadImage(@RequestBody MultipartFile imageFile) throws IOException {
        String result = imageService.uploadImageToFileSystem(imageFile);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

//    @GetMapping("/{imageName}")
//    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
//
//        byte[] imageBytes;
//
//        try {
//            imageBytes = imageService.getImage(imageName);
//        } catch (IOException e) {
//            throw new ResourceNotFoundException("No image found");
//        }
//
//        return new ResponseEntity<>(imageBytes, HttpStatus.OK);
//    }

    @GetMapping("/{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName) throws IOException {

        byte[] imageBytes;

        try {
            imageBytes = imageService.getImage(imageName);
        } catch (IOException e) {
            throw new ResourceNotFoundException("No image found");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }

    @DeleteMapping("/{imageName}")
    public ResponseEntity<String> deleteImage(@PathVariable String imageName) throws IOException {

        try {
            byte[] imageBytes = imageService.getImage(imageName);
        } catch (IOException e) {
            throw new ResourceNotFoundException("No image found");
        }

        imageService.deleteImage(imageName);

        return new ResponseEntity<>("Image is deleted successfully.", HttpStatus.OK);
    }

    // For delete image, refer to Product Service Controller

}
