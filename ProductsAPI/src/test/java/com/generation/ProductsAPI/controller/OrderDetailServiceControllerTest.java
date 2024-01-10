package com.generation.ProductsAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation.ProductsAPI.exception.ResourceNotFoundException;
import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.model.OrderDetail;
import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.service.OrderDetailService;
import com.generation.ProductsAPI.service.OrderService;
import com.generation.ProductsAPI.service.ProductService;
import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.*;

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

@WebMvcTest(OrderDetailServiceController.class)
public class OrderDetailServiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OrderDetailService orderDetailService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductService productService;

    private OrderDetail orderDetail1, orderDetail2, updatedOrderDetail;

    private Order order1;

    private Product product1, product2;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init(){

        product1 = new Product("Grey casual shirt", "Tommy Hilfiger", new BigDecimal("50.10"), "casual-shirt.jpg");

        product2 = new Product("Blue khakis", "Adidas", new BigDecimal("27.83"), "blue-khakis.jpg");

        order1 = new Order("Darren", new BigDecimal("261.62"), new BigDecimal("4.99"), new BigDecimal("266.61"), Instant.parse("2023-12-26T10:15:30.00Z"));

        orderDetail1 = new OrderDetail("XL", 3, new BigDecimal("150.30"));
        orderDetail1.setOrder(order1);
        orderDetail1.setProduct(product1);

        orderDetail2 = new OrderDetail("M", 4, new BigDecimal("111.32"));
        orderDetail2.setOrder(order1);
        orderDetail2.setProduct(product2);

        updatedOrderDetail = new OrderDetail("XL", 5, new BigDecimal("250.50"));
        updatedOrderDetail.setOrder(order1);
        updatedOrderDetail.setProduct(product1);
    }


    @Test
    void getAllOrderDetails() throws Exception {

        List<OrderDetail> list = new ArrayList<>();
        list.add(orderDetail1);
        list.add(orderDetail2);

        when(orderDetailService.getAllOrderDetails()).thenReturn(list);

        ResultActions response = mockMvc.perform(get("/api/order-details").contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    void getSingleOrderDetail() throws Exception {

        when(orderDetailService.getOrderDetail(any(Integer.class))).thenReturn(Optional.ofNullable(orderDetail1));

        ResultActions response = mockMvc.perform(get("/api/order-details/{id}", 1).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size", is(orderDetail1.getSize())))
                .andExpect(jsonPath("$.quantity", is(orderDetail1.getQuantity())))
                .andExpect(jsonPath("$.price", is(orderDetail1.getPrice().doubleValue())));
    }

    @Test
    void updateOrderDetail() throws Exception {

        when(orderDetailService.updateOrderDetail(any(Integer.class), any(OrderDetail.class)))
                .thenReturn(Optional.ofNullable(updatedOrderDetail));

        ResultActions response = mockMvc.perform(put("/api/order-details/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedOrderDetail)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(updatedOrderDetail.getSize())))
                .andExpect(jsonPath("$.quantity", is(updatedOrderDetail.getQuantity())))
                .andExpect(jsonPath("$.price", is(updatedOrderDetail.getPrice().doubleValue())));
    }

    @Test
    void deleteOrderDetail() throws Exception {

        when(orderDetailService.getOrderDetail(any(Integer.class))).thenReturn(Optional.ofNullable(orderDetail1));
        doNothing().when(orderDetailService).deleteOrderDetail(any(Integer.class));

        ResultActions response = mockMvc.perform(delete("/api/order-details/{id}", 1));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("Order Detail is deleted successfully."));
    }

    @Test
    void getAllOrderDetailsByOrderId() throws Exception {

        when(orderService.getOrder(any(Integer.class))).thenReturn(Optional.ofNullable(order1));

        List<OrderDetail> list = new ArrayList<>();
        list.add(orderDetail1);
        list.add(orderDetail2);

        when(orderDetailService.getAllOrderDetailsByOrderId(any(Integer.class))).thenReturn(list);

        ResultActions response = mockMvc.perform(get("/api/orders/{orderId}/order-details", 1).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    void createNewOrderDetailsByOrderId() throws Exception {

        when(orderService.getOrder(any(Integer.class))).thenReturn(Optional.ofNullable(order1));

        when(productService.getProduct(any(Integer.class))).thenReturn(Optional.ofNullable(product1));

        when(orderDetailService.createNewOrderDetail(any(OrderDetail.class))).thenReturn(orderDetail1);

        ResultActions response = mockMvc.perform(post("/api/orders/{orderId}/products/{productId}/order-details", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDetail1)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.size", is(orderDetail1.getSize())))
                .andExpect(jsonPath("$.quantity", is(orderDetail1.getQuantity())))
                .andExpect(jsonPath("$.price", is(orderDetail1.getPrice().doubleValue())));
//  cannot retrieve productId & orderId
//                .andExpect(jsonPath("$.product.id", is(orderDetail1.getProduct().getId())))
//                .andExpect(jsonPath("$.order.id", is(orderDetail1.getOrder().getId())));
    }

    @Test
    void deleteAllOrderDetailByOrderId() throws Exception {

        when(orderService.getOrder(any(Integer.class))).thenReturn(Optional.ofNullable(order1));

        doNothing().when(orderDetailService).deleteAllOrderDetailByOrderId(any(Integer.class));

        ResultActions response = mockMvc.perform(delete("/api/orders/{orderId}/order-details", 1));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("All order details from Order 1 are deleted successfully."));
    }

}
