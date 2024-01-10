package com.generation.ProductsAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation.ProductsAPI.model.OrderDetail;
import com.generation.ProductsAPI.service.OrderDetailService;
import com.generation.ProductsAPI.service.OrderService;
import com.generation.ProductsAPI.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

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

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init(){

        orderDetail1 = new OrderDetail("XL", 3, new BigDecimal("30.87"));
        orderDetail2 = new OrderDetail("M", 4, new BigDecimal("150.20"));
        updatedOrderDetail = new OrderDetail("XL", 5, new BigDecimal("50.12"));
    }


    @Test
    void getAllOrderDetails() throws Exception {

    }

    @Test
    void getSingleOrderDetail() throws Exception {

    }

    @Test
    void updateOrderDetail() throws Exception {

    }

    @Test
    void deleteOrderDetail() throws Exception {

    }

    @Test
    void getAllOrderDetailsByOrderId() throws Exception {

    }

    @Test
    void createNewOrderDetailsByOrderId() throws Exception {

    }

    @Test
    void deleteAllOrderDetailByOrderId() throws Exception {

    }

}
