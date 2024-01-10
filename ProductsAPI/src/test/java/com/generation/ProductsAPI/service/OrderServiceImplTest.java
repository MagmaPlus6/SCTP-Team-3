package com.generation.ProductsAPI.service;

import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.repository.OrderRepository;
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
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepo;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    private Order order1, order2, updatedOrder;

    @BeforeEach
    void init(){

        order1 = new Order("Darren", new BigDecimal("37.50"), new BigDecimal("4.99"), new BigDecimal("42.49"), Instant.parse("2023-12-26T10:15:30.00Z"));
        order1.setId(1);

        order2 = new Order("Darren", new BigDecimal("112.80"), new BigDecimal("4.99"), new BigDecimal("117.79"), Instant.parse("2024-01-02T11:19:42.12Z"));
        order2.setId(2);

        updatedOrder = new Order("Darren", new BigDecimal("39.50"), new BigDecimal("4.99"), new BigDecimal("44.49"), Instant.parse("2023-12-26T10:15:30.00Z"));
        updatedOrder.setId(1);
    }

    @Test
    void getAllOrders() {

        List<Order> list = new ArrayList<>();
        list.add(order1);
        list.add(order2);

        when(orderRepo.findAll()).thenReturn(list);
        List<Order> results = orderServiceImpl.getAllOrders();

        assertNotNull(results);
        assertEquals(2, results.size());
        assertThat(results).containsExactlyInAnyOrder(order1, order2);
    }

    @Test
    void getOrder() {

        when(orderRepo.findById(any(Integer.class))).thenReturn(Optional.of(order1));

        Optional<Order> currentOrder = orderServiceImpl.getOrder(order1.getId());

        assertNotNull(currentOrder);
        assertFalse(currentOrder.isEmpty());
        assertThat(currentOrder.get().getId()).isEqualTo(1);
    }

    @Test
    void createNewOrder() {

        when(orderRepo.save(any(Order.class))).thenReturn(order1);

        Order newOrder = orderServiceImpl.createNewOrder(order1);

        assertNotNull(newOrder);
        assertThat(newOrder.getSubtotal()).isEqualTo("37.50");
    }

    @Test
    void updateOrder() {

        when(orderRepo.findById(any(Integer.class))).thenReturn(Optional.ofNullable(order1));
        when(orderRepo.save(any(Order.class))).thenReturn(order1);

        BigDecimal originalSubtotal = order1.getSubtotal();
        order1.setSubtotal(new BigDecimal("39.50"));
        Optional<Order> currentOrder = orderServiceImpl.updateOrder(order1.getId(), order1);

        assertNotNull(currentOrder);
        assertEquals(updatedOrder.getSubtotal(), currentOrder.get().getSubtotal());
        assertNotEquals(originalSubtotal, currentOrder.get().getSubtotal());
    }

    @Test
    void deleteOrder() {

        doNothing().when(orderRepo).deleteById(any(Integer.class));

        orderServiceImpl.deleteOrder(order1.getId());

        verify(orderRepo, times(1)).deleteById(order1.getId());
    }
}