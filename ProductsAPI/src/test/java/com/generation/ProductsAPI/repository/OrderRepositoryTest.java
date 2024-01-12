package com.generation.ProductsAPI.repository;

import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private Order order1, order2, updatedOrder;

    @BeforeEach
    void init(){
        order1 = new Order("Darren", new BigDecimal("37.50"), new BigDecimal("4.99"), new BigDecimal("42.49"), Instant.parse("2023-12-26T10:15:30.00Z"));

        order2 = new Order("Darren", new BigDecimal("112.80"), new BigDecimal("4.99"), new BigDecimal("117.79"), Instant.parse("2024-01-02T11:19:42.12Z"));
    }


    @Test
    void saveOrder() {
        // When : Action of behavious that we are going to test
        Order saveOrder = orderRepository.save(order1);

        // Then : Verify the output
        assertNotNull(saveOrder);
        assertThat(saveOrder.getId()).isGreaterThan(0);
    }


    @Test
    void findAllOrder() {
        // Given : Setup object or precondition
        orderRepository.save(order1);
        orderRepository.save(order2);

        // When : Action of behavious that we are going to test
        List<Order> orders = orderRepository.findAll();

        // Then : Verify the output
        assertFalse(orders.isEmpty());
        assertThat(orders.size()).isGreaterThan(0);
    }


    @Test
    void findByOrderId() {
        // Given : Setup object or precondition
        orderRepository.save(order1);

        // When : Action of behavious that we are going to test
        Optional<Order> getOrder = orderRepository.findById(order1.getId());

        // Then : Verify the output
        assertNotNull(getOrder);
    }

    @Test
    void updateOrder() {
        // Given: Setup object or precondition
        orderRepository.save(order1);

        // When: Action or behavior that we are going to test
        Order orderToUpdate = orderRepository.findById(order1.getId()).get();
        orderToUpdate.setSubtotal(new BigDecimal("39.50"));
        orderToUpdate.setTotal(new BigDecimal("44.49"));

        Order updatedOrder = orderRepository.save(orderToUpdate);

        // Then: Verify the output or expected result
        assertNotNull(updatedOrder);
        assertEquals(new BigDecimal("39.50"), updatedOrder.getSubtotal());
        assertEquals(new BigDecimal("44.49"), updatedOrder.getTotal());
    }

    @Test
    void deleteOrder() {
        // Given: Setup object or precondition
        orderRepository.save(order1);

        // When: Action or behavior that we are going to test
        orderRepository.deleteById(order1.getId());
        Optional<Order> getOrder1 = orderRepository.findById(order1.getId());

        // Then: Verify the output or expected result
        assertThat(getOrder1).isEmpty();
    }

}