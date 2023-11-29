package com.generation.ProductsAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// this entire class is testing for request/routes, NOT testing for database persistence
@WebMvcTest // place the @AutoConfigureMockMvc annotation on this class under test
class ProductServiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean // annotate that ProductService is what we want to set up as a mock service
    private ProductService productService;

    private Product product1, product2, updatedProduct;

    @Autowired
    private ObjectMapper objectMapper; // used for sending data in String format when we run our tests

    @BeforeEach
        // set up some initializations before we can run the test
    void init(){
        product1 = new Product("Grey casual shirt", "Tommy Hilfiger", new BigDecimal("50.10"), "casual-shirt.jpg");
        product2 = new Product("Blue Khakis", "Adidas", new BigDecimal("27.83"), "blue-khakis.jpg");
        updatedProduct = new Product("Grey casual shirt", "Tommy Hilfiger", new BigDecimal("45.10"), "casual-shirt.jpg");
    }

    @Test
    void getAllProducts() throws Exception{

        // given - precondition or setup
        List<Product> list = new ArrayList<>(); // exists for this test only
        list.add(product1);
        list.add(product2);

        when(productService.getAllProducts()).thenReturn(list);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON));

        // then - verify the output
        response.andExpect(status().isOk()) // if isOk changed to isNoContent, will show error 200
                .andDo(print()) // andDo(print()) will print the request and response. This is helpful to get a detailed view in case of an error.
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    void getSingleProduct() throws Exception {

        when(productService.getProduct(any(Integer.class))).thenReturn(Optional.ofNullable(product1));

        ResultActions response = mockMvc.perform(get("/products/{id}", 1).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(product1.getId())))
                .andExpect(jsonPath("$.name", is(product1.getName())))
                .andExpect(jsonPath("$.brand", is(product1.getBrand())))
                .andExpect(jsonPath("$.price", is(product1.getPrice().doubleValue())))
                .andExpect(jsonPath("$.image", is(product1.getImage())));
    }

    @Test
    void sortAllProductsByPriceAscend() {
    }

    @Test
    void sortAllProductsByPriceDescend() {
    }

    @Test
    void getProductsWithName() {
    }

    @Test
    void getProductsWithBrand() {
    }

    @Test
    void createNewProduct() throws Exception{

        when(productService.createNewProduct(any(Product.class))).thenReturn(product1);

        ResultActions response = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product1)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(product1.getId())))
                .andExpect(jsonPath("$.name", is(product1.getName())))
                .andExpect(jsonPath("$.brand", is(product1.getBrand())))
                .andExpect(jsonPath("$.price", is(product1.getPrice().doubleValue())))
                .andExpect(jsonPath("$.image", is(product1.getImage())));
    }

    @Test
    void updateProduct() throws Exception{

        when(productService.updateProduct(any(Integer.class), any(Product.class)))
                .thenReturn(Optional.ofNullable(updatedProduct));

        // this is what the unit test will perform and the expected output should be the same as the above unit test case
        ResultActions response = mockMvc.perform(put("/products/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedProduct.getId())))
                .andExpect(jsonPath("$.name", is(updatedProduct.getName())))
                .andExpect(jsonPath("$.brand", is(updatedProduct.getBrand())))
                .andExpect(jsonPath("$.price", is(updatedProduct.getPrice().doubleValue())))
                .andExpect(jsonPath("$.image", is(updatedProduct.getImage())));
    }

    @Test
    void deleteProduct() throws Exception{

        when(productService.getProduct(any(Integer.class))).thenReturn(Optional.ofNullable(product1));
        doNothing().when(productService).deleteProduct(any(Integer.class));

        ResultActions response = mockMvc.perform(delete("/products/{id}", 1));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("Product is deleted successfully."));
    }
}