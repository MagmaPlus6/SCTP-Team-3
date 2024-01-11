package com.generation.ProductsAPI.controller;

import com.generation.ProductsAPI.service.ImageService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ImageServiceController.class)
public class ImageServiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    private String imageName;

    private byte[] imageBytes;

    @BeforeEach
        // set up some initializations before we can run the test
    void init(){
        imageName = "test.jpg";
        imageBytes = "test".getBytes();

    }

    @Test
    void uploadImage() throws Exception {

        // Create a sample image file
        MockMultipartFile imageFile = new MockMultipartFile("imageFile", imageName, "image/jpeg", "Hello, world!".getBytes());

        // Mock the behavior of your ImageService
        when(imageService.uploadImageToFileSystem(any(MultipartFile.class))).thenReturn(imageName);

        // Perform the POST request
        ResultActions response = mockMvc.perform(multipart("/api/image")
                .file(imageFile));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(imageName));
    }

    @Test
    void getImage() throws Exception {

        // Arrange
        when(imageService.getImage(imageName)).thenReturn(imageBytes);

        // Act & Assert
        ResultActions response = mockMvc.perform(get("/api/image/{imageName}", imageName));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG));
    }

    @Test
    void deleteImage() throws Exception {

        // Arrange
        when(imageService.getImage(imageName)).thenReturn(imageBytes);

        doNothing().when(imageService).deleteImage(imageName);

        // Act & Assert
        ResultActions response = mockMvc.perform(delete("/api/image/{imageName}", imageName));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("Image is deleted successfully."));
    }

}
