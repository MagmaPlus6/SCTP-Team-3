package com.generation.ProductsAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderServiceController.class)
public class OrderServiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private Order order1, order2, updatedOrder;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init(){

        order1 = new Order("Darren", new BigDecimal("37.50"), new BigDecimal("4.99"), new BigDecimal("42.49"), Instant.parse("2023-12-26T10:15:30.00Z"));
        order2 = new Order("Darren", new BigDecimal("112.80"), new BigDecimal("4.99"), new BigDecimal("117.79"), Instant.parse("2024-01-02T11:19:42.12Z"));
        updatedOrder = new Order("Darren", new BigDecimal("39.50"), new BigDecimal("4.99"), new BigDecimal("44.49"), Instant.parse("2023-12-26T10:15:30.00Z"));
    }

    @Test
    void getAllOrders() throws Exception {

        List<Order> list = new ArrayList<>();
        list.add(order1);
        list.add(order2);

        when(orderService.getAllOrders()).thenReturn(list);

        ResultActions response = mockMvc.perform(get("/api/orders").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    void getSingleOrder() throws Exception {

        when(orderService.getOrder(any(Integer.class))).thenReturn(Optional.ofNullable(order1));

        ResultActions response = mockMvc.perform(get("/api/orders/{id}", 1).contentType(MediaType.APPLICATION_JSON));

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

        when(orderService.createNewOrder(any(Order.class))).thenReturn(order2);

        ResultActions response = mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order2)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(order2.getId())))
                .andExpect(jsonPath("$.username", is(order2.getUsername())))
                .andExpect(jsonPath("$.subtotal", is(order2.getSubtotal().doubleValue())))
                .andExpect(jsonPath("$.shippingFee", is(order2.getShippingFee().doubleValue())))
                .andExpect(jsonPath("$.total", is(order2.getTotal().doubleValue())))
                .andExpect(jsonPath("$.orderDate", is(order2.getOrderDate().toString())));
    }

    @Test
    void updateOrder() throws Exception {

        when(orderService.updateOrder(any(Integer.class), any(Order.class)))
                .thenReturn(Optional.ofNullable(updatedOrder));

        ResultActions response = mockMvc.perform(put("/api/orders/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedOrder)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedOrder.getId())))
                .andExpect(jsonPath("$.username", is(updatedOrder.getUsername())))
                .andExpect(jsonPath("$.subtotal", is(updatedOrder.getSubtotal().doubleValue())))
                .andExpect(jsonPath("$.shippingFee", is(updatedOrder.getShippingFee().doubleValue())))
                .andExpect(jsonPath("$.total", is(updatedOrder.getTotal().doubleValue())))
                .andExpect(jsonPath("$.orderDate", is(updatedOrder.getOrderDate().toString())));
    }

    @Test
    void deleteOrder() throws Exception {

        when(orderService.getOrder(any(Integer.class))).thenReturn(Optional.ofNullable(order1));
        doNothing().when(orderService).deleteOrder(any(Integer.class));

        ResultActions response = mockMvc.perform(delete("/api/orders/{id}", 1));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("Order is deleted successfully."));
    }

}
