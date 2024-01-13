package com.generation.ProductsAPI.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.repository.ProductRepository;
import com.generation.ProductsAPI.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductServiceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    private Product product1, product2, product3, updatedProduct;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init(){
        product1 = new Product("Grey casual shirt", "Tommy Hilfiger", new BigDecimal("0.10"), "casual-shirt.jpg");
        product2 = new Product("Blue khakis", "Adidas", new BigDecimal("100.83"), "blue-khakis.jpg");
        product3 = new Product("Grey belt", "Adidas", new BigDecimal("11"), "grey-belt.jpg");

        updatedProduct = new Product("Grey casual short-sleeved shirt", "Tommy Hilfiger", new BigDecimal("0.10"), "casual-shirt.jpg");

//        productRepository.deleteAll();
    }

    @AfterEach
    void deleteEntities() {
        productRepository.delete(product1);
        productRepository.delete(product2);
        productRepository.delete(product3);
        productRepository.delete(updatedProduct);

        // Created this because productRepository.delete(product1) does not work for createNewProduct() test
        productRepository.deleteByName("Grey casual shirt");
    }

    @Test
    void getAllProducts() throws Exception {

        productService.createNewProduct(product1);
        productService.createNewProduct(product2);

        ResultActions response = mockMvc.perform(get("/api/products").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[?(@.name == \'Grey casual shirt\')]").exists())
                .andExpect(jsonPath("$[?(@.name == \'Blue khakis\')]").exists());
    }

    @Test
    void getSingleProduct() throws Exception {

        productService.createNewProduct(product1);

        ResultActions response = mockMvc.perform(get("/api/products/{id}", product1.getId()).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(product1.getId())))
                .andExpect(jsonPath("$.name", is(product1.getName())))
                .andExpect(jsonPath("$.brand", is(product1.getBrand())))
                .andExpect(jsonPath("$.price", is(product1.getPrice().doubleValue())))
                .andExpect(jsonPath("$.image", is(product1.getImage())));
    }

    @Test
    void sortAllProductsByPriceAscend() throws Exception {

        productService.createNewProduct(product1);
        productService.createNewProduct(product2);

        ResultActions response = mockMvc.perform(get("/api/products/sortby_price_asc").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].price", is(product1.getPrice().doubleValue())))
                .andExpect(jsonPath("$[-1].price", is(product2.getPrice().doubleValue())));
    }

    @Test
    void sortAllProductsByPriceDescend() throws Exception {

        productService.createNewProduct(product1);
        productService.createNewProduct(product2);

        ResultActions response = mockMvc.perform(get("/api/products/sortby_price_desc").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].price", is(product2.getPrice().doubleValue())))
                .andExpect(jsonPath("$[-1].price", is(product1.getPrice().doubleValue())));
    }

    @Test
    void searchProducts() throws Exception {

        productService.createNewProduct(product1);
        productService.createNewProduct(product2);
        productService.createNewProduct(product3);

        ResultActions response = mockMvc.perform(get("/api/products/").param("name", "Grey").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    void createNewProduct() throws Exception {

        ResultActions response = mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product1)));

        response.andDo(print())
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", is(product1.getId())))
                // don't test for $.id because product1 has no id when initialized
                .andExpect(jsonPath("$.name", is(product1.getName())))
                .andExpect(jsonPath("$.brand", is(product1.getBrand())))
                .andExpect(jsonPath("$.price", is(product1.getPrice().doubleValue())))
                .andExpect(jsonPath("$.image", is(product1.getImage())));
    }

    @Test
    void updateProduct() throws Exception {

        productService.createNewProduct(product1);

        ResultActions response = mockMvc.perform(put("/api/products/{id}",product1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)));

        response.andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(updatedProduct.getId())))
                // don't test for $.id because product1 has no id when initialized
                .andExpect(jsonPath("$.name", is(updatedProduct.getName())))
                .andExpect(jsonPath("$.brand", is(updatedProduct.getBrand())))
                .andExpect(jsonPath("$.price", is(updatedProduct.getPrice().doubleValue())))
                .andExpect(jsonPath("$.image", is(updatedProduct.getImage())));
    }

    @Test
    void deleteProduct() throws Exception {

        productService.createNewProduct(product1);

        ResultActions response = mockMvc.perform(delete("/api/products/{id}", product1.getId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("Product is deleted successfully."));
    }

}
