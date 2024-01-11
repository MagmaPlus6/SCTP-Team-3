package com.generation.ProductsAPI.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @InjectMocks
    private ImageServiceImpl imageServiceImpl;

    private String imageDirectory, imageName;

    private Path imagePath;

    private byte[] imageBytes;

    @BeforeEach
    void init(){
        imageDirectory = "src/main/resources/static/image";
        imageName = "image.jpg";
        imagePath = Paths.get(imageDirectory).resolve(imageName);
        imageBytes = "test".getBytes();
    }

    @Test
    void uploadImageToFileSystem() throws IOException {

        // Arrange
        MultipartFile imageFile = mock(MultipartFile.class);

        // Mock file input stream
        byte[] imageBytes = "test".getBytes();
        when(imageFile.getInputStream()).thenReturn(new ByteArrayInputStream(imageBytes));

        // Act
        String result = imageServiceImpl.uploadImageToFileSystem(imageFile);

        // Assert
        assertNotEquals(imageName, result);
        assertNotNull(result);
        verify(imageFile, times(1)).getInputStream();
    }

    @Test
    void getImage() throws IOException {

        // Arrange
        Files.write(imagePath, imageBytes);

        // Act
        byte[] result = imageServiceImpl.getImage(imageName);

        // Assert
        assertNotNull(result);
        assertArrayEquals(imageBytes, result);
    }

    @Test
    void deleteImage() throws IOException {

        // Arrange
        Files.write(imagePath, imageBytes);

        // Act
        imageServiceImpl.deleteImage(imageName);

        // Assert
        assertFalse(Files.exists(imagePath));
    }

}