package com.generation.ProductsAPI.controller;

import com.generation.ProductsAPI.exception.ResourceNotFoundException;
import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//    @GetMapping("/images/{id}")
//    public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long id) {
//        Image image = imageService.getImage(id);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + image.getImageName())
//                .body(new ByteArrayResource(image.getImage()));
//    }

    // For delete image, refer to Product Service Controller

    @GetMapping("/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {

            byte[] imageBytes = imageService.getImage(imageName);
            return new ResponseEntity<>(imageBytes, HttpStatus.OK);
    }

    @DeleteMapping("/{imageName}")
    public ResponseEntity<String> deleteImage(@PathVariable String imageName) throws IOException {

        imageService.deleteImage(imageName);

        return new ResponseEntity<>("Image is deleted successfully.", HttpStatus.OK);
    }

}
