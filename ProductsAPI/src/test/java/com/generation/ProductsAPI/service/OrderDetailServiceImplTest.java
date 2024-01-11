package com.generation.ProductsAPI.service;

import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.model.OrderDetail;
import com.generation.ProductsAPI.repository.OrderDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OrderDetailServiceImplTest {

    @Mock
    private OrderDetailRepository orderDetailRepo;

    @InjectMocks
    private OrderDetailServiceImpl orderDetailServiceImpl;

    private OrderDetail orderDetail1, orderDetail2, orderDetail3, updatedOrderDetail;

    private Order order1;

    @BeforeEach
    void init(){

//        product1 = new Product("Grey casual shirt", "Tommy Hilfiger", new BigDecimal("50.10"), "casual-shirt.jpg");

//        product2 = new Product("Blue khakis", "Adidas", new BigDecimal("27.83"), "blue-khakis.jpg");

        order1 = new Order("Darren", new BigDecimal("261.62"), new BigDecimal("4.99"), new BigDecimal("266.61"), Instant.parse("2023-12-26T10:15:30.00Z"));
        order1.setId(1);

        orderDetail1 = new OrderDetail("XL", 3, new BigDecimal("150.30"));
        orderDetail1.setId(1);
        orderDetail1.setOrder(order1);
//        orderDetail1.setProduct(product1);

        orderDetail2 = new OrderDetail("M", 4, new BigDecimal("111.32"));
        orderDetail2.setId(2);
        orderDetail2.setOrder(order1);
//        orderDetail2.setProduct(product2);

        updatedOrderDetail = new OrderDetail("XL", 5, new BigDecimal("250.50"));
        updatedOrderDetail.setId(1);
        updatedOrderDetail.setOrder(order1);
//        updatedOrderDetail.setProduct(product1);

    }


    @Test
    void getAllOrderDetails() {

        List<OrderDetail> list = new ArrayList<>();
        list.add(orderDetail1);
        list.add(orderDetail2);

        when(orderDetailRepo.findAll()).thenReturn(list);
        List<OrderDetail> results = orderDetailServiceImpl.getAllOrderDetails();

        assertNotNull(results);
        assertEquals(2, results.size());
        assertThat(results).containsExactlyInAnyOrder(orderDetail1, orderDetail2);
    }

    @Test
    void getOrderDetail() {

        when(orderDetailRepo.findById(any(Integer.class))).thenReturn(Optional.of(orderDetail1));

        Optional<OrderDetail> currentOrderDetail = orderDetailServiceImpl.getOrderDetail(orderDetail1.getId());

        assertNotNull(currentOrderDetail);
        assertFalse(currentOrderDetail.isEmpty());
        assertThat(currentOrderDetail.get().getId()).isEqualTo(1);
    }

    @Test
    void createNewOrderDetail() {

        when(orderDetailRepo.save(any(OrderDetail.class))).thenReturn(orderDetail1);

        OrderDetail newOrderDetail = orderDetailServiceImpl.createNewOrderDetail(orderDetail1);

        assertNotNull(newOrderDetail);
        assertThat(newOrderDetail.getQuantity()).isEqualTo(3);
        assertThat(newOrderDetail.getSize()).isEqualTo("XL");
    }

    @Test
    void updateOrderDetail() {

        when(orderDetailRepo.findById(any(Integer.class))).thenReturn(Optional.ofNullable(orderDetail1));
        when(orderDetailRepo.save(any(OrderDetail.class))).thenReturn(orderDetail1);

        Integer originalQuantity = orderDetail1.getQuantity();
        BigDecimal originalPrice = orderDetail1.getPrice();
        orderDetail1.setQuantity(5);
        orderDetail1.setPrice(new BigDecimal("250.50"));
        Optional<OrderDetail> currentOrderDetail = orderDetailServiceImpl.updateOrderDetail(orderDetail1.getId(), orderDetail1);

        assertNotNull(currentOrderDetail);
        assertEquals(updatedOrderDetail.getQuantity(), currentOrderDetail.get().getQuantity());
        assertNotEquals(originalQuantity, currentOrderDetail.get().getQuantity());

        assertEquals(updatedOrderDetail.getPrice(), currentOrderDetail.get().getPrice());
        assertNotEquals(originalPrice, currentOrderDetail.get().getPrice());
    }

    @Test
    void deleteOrderDetail() {

        doNothing().when(orderDetailRepo).deleteById(any(Integer.class));

        orderDetailServiceImpl.deleteOrderDetail(orderDetail1.getId());

        verify(orderDetailRepo, times(1)).deleteById(orderDetail1.getId());
    }

    @Test
    void getAllOrderDetailsByOrderId() {

        List<OrderDetail> list = new ArrayList<>();
        list.add(orderDetail1);
        list.add(orderDetail2);

        when(orderDetailRepo.findByOrderId(any(Integer.class))).thenReturn(list);
        List<OrderDetail> results = orderDetailServiceImpl.getAllOrderDetailsByOrderId(order1.getId());

        assertNotNull(results);
        assertEquals(2, results.size());
        assertThat(results).containsExactlyInAnyOrder(orderDetail1, orderDetail2);
    }

    @Test
    void deleteAllOrderDetailByOrderId() {

        doNothing().when(orderDetailRepo).deleteByOrderId(any(Integer.class));

        orderDetailServiceImpl.deleteAllOrderDetailByOrderId(order1.getId());

        verify(orderDetailRepo, times(1)).deleteByOrderId(order1.getId());
    }

}