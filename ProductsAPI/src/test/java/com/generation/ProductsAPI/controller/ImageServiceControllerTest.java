package com.generation.ProductsAPI.controller;

import com.generation.ProductsAPI.service.ImageService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ImageServiceController.class)
public class ImageServiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    @Test
    void uploadImage() throws Exception {

        // Create a sample image file
        String fileName = "test.jpg";
        MockMultipartFile imageFile = new MockMultipartFile("imageFile", fileName, "image/jpeg", "Hello, world!".getBytes());

        // Mock the behavior of your ImageService
        when(imageService.uploadImageToFileSystem(any(MultipartFile.class))).thenReturn(fileName);

        // Perform the POST request
        ResultActions response = mockMvc.perform(multipart("/api/image")
                .file(imageFile));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(fileName));
    }

}
