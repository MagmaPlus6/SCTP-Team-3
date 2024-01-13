package com.generation.ProductsAPI.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.repository.OrderRepository;
import com.generation.ProductsAPI.service.OrderService;
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
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderServiceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    private Order order1, order2, updatedOrder;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init(){
        order1 = new Order("TestName1", new BigDecimal("10.10"), new BigDecimal("1.01"), new BigDecimal("11.11"));

        order2 = new Order("TestName2", new BigDecimal("20.20"), new BigDecimal("2.02"), new BigDecimal("22.22"));

        updatedOrder = new Order("TestName1", new BigDecimal("30.30"), new BigDecimal("3.03"), new BigDecimal("33.33"));
    }

    @AfterEach
    void deleteEntities() {
        orderRepository.delete(order1);
        orderRepository.delete(order2);
        orderRepository.delete(updatedOrder);

        // Created this because orderRepository.delete(order1) does not work for createNewProduct() test
        orderRepository.deleteByUsername("TestName1");
    }

    @Test
    void getAllOrders() throws Exception {

        orderService.createNewOrder(order1);
        orderService.createNewOrder(order2);

        ResultActions response = mockMvc.perform(get("/api/orders").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[?(@.username == \'TestName1\')]").exists())
                .andExpect(jsonPath("$[?(@.username == \'TestName2\')]").exists());
    }

    @Test
    void getSingleOrder() throws Exception {

        orderService.createNewOrder(order1);

        ResultActions response = mockMvc.perform(get("/api/orders/{id}", order1.getId()).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(order1.getId())))
                .andExpect(jsonPath("$.username", is(order1.getUsername())))
                .andExpect(jsonPath("$.subtotal", is(order1.getSubtotal().doubleValue())))
                .andExpect(jsonPath("$.shippingFee", is(order1.getShippingFee().doubleValue())))
                .andExpect(jsonPath("$.total", is(order1.getTotal().doubleValue())))
                .andExpect(jsonPath("$.orderDate", is(order1.getOrderDate().toString())));
    }

    @Test
    void createNewOrder() throws Exception {

        ResultActions response = mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order1)));

        response.andDo(print())
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", is(order1.getId())))
                // don't test for $.id because order1 has no id when initialized
                .andExpect(jsonPath("$.username", is(order1.getUsername())))
                .andExpect(jsonPath("$.subtotal", is(order1.getSubtotal().doubleValue())))
                .andExpect(jsonPath("$.shippingFee", is(order1.getShippingFee().doubleValue())))
                .andExpect(jsonPath("$.total", is(order1.getTotal().doubleValue())));
//                .andExpect(jsonPath("$.orderDate", is(order1.getOrderDate().toString())));
                  // $.orderdate is the date when test is run
    }

    @Test
    void updateOrder() throws Exception {

        orderService.createNewOrder(order1);

        ResultActions response = mockMvc.perform(put("/api/orders/{id}",order1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedOrder)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(updatedOrder.getUsername())))
                .andExpect(jsonPath("$.subtotal", is(updatedOrder.getSubtotal().doubleValue())))
                .andExpect(jsonPath("$.shippingFee", is(updatedOrder.getShippingFee().doubleValue())))
                .andExpect(jsonPath("$.total", is(updatedOrder.getTotal().doubleValue())));
    }

    @Test
    void deleteOrder() throws Exception {

        orderService.createNewOrder(order1);

        ResultActions response = mockMvc.perform(delete("/api/orders/{id}", order1.getId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("Order is deleted successfully."));
    }






}
