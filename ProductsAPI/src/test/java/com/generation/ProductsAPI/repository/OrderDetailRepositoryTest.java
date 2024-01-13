package com.generation.ProductsAPI.repository;

import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.model.OrderDetail;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class OrderDetailRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    private Product product1, product2;

    private Order order1, order2;

    private OrderDetail orderDetail1, orderDetail2, orderDetail3;

    @BeforeEach
    void init(){

        product1 = new Product("Grey test product 1", "Test Brand 1", new BigDecimal("0.10"), "test-product-1.jpg");
        product2 = new Product("Blue test product 2", "Test Brand 2", new BigDecimal("100.23"), "test-product-2.jpg");
        productRepository.save(product1);
        productRepository.save(product2);

        order1 = new Order("Darren", new BigDecimal("261.62"), new BigDecimal("4.99"), new BigDecimal("266.61"));
        order2 = new Order("Darren", new BigDecimal("112.80"), new BigDecimal("4.99"), new BigDecimal("117.79"));

        orderRepository.save(order1);
        orderRepository.save(order2);

        orderDetail1 = new OrderDetail("XL", 3, new BigDecimal("150.30"));
        orderDetail1.setOrder(order1);
        orderDetail1.setProduct(product1);

        orderDetail2 = new OrderDetail("M", 4, new BigDecimal("111.32"));
        orderDetail2.setOrder(order1);
        orderDetail2.setProduct(product2);

        orderDetail3 = new OrderDetail("S", 2, new BigDecimal("29.40"));
        orderDetail3.setOrder(order2);
        orderDetail3.setProduct(product1);
    }

    @Test
    void saveOrderDetail() {
        // When : Action of behavious that we are going to test
        OrderDetail saveOrderDetail = orderDetailRepository.save(orderDetail1);

        // Then : Verify the output
        assertNotNull(saveOrderDetail);
        assertThat(saveOrderDetail.getId()).isGreaterThan(0);
    }


    @Test
    void findAllOrderDetail() {
        // Given : Setup object or precondition
        orderDetailRepository.save(orderDetail1);
        orderDetailRepository.save(orderDetail2);

        // When : Action of behavious that we are going to test
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();

        // Then : Verify the output
        assertFalse(orderDetails.isEmpty());
        assertThat(orderDetails.size()).isGreaterThan(0);
    }

    @Test
    void findByOrderDetailId() {
        // Given : Setup object or precondition
        orderDetailRepository.save(orderDetail1);

        // When : Action of behavious that we are going to test
        Optional<OrderDetail> getOrderDetail = orderDetailRepository.findById(orderDetail1.getId());

        // Then : Verify the output
        assertNotNull(getOrderDetail);
    }

    @Test
    void updateOrderDetail() {
        // Given: Setup object or precondition
        orderDetailRepository.save(orderDetail1);

        // When: Action or behavior that we are going to test
        OrderDetail orderDetailToUpdate = orderDetailRepository.findById(orderDetail1.getId()).get();
        orderDetailToUpdate.setQuantity(5);
        orderDetailToUpdate.setPrice(new BigDecimal("250.50"));

        OrderDetail updatedOrderDetail = orderDetailRepository.save(orderDetailToUpdate);

        // Then: Verify the output or expected result
        assertNotNull(updatedOrderDetail);
        assertEquals(5, updatedOrderDetail.getQuantity());
        assertEquals(new BigDecimal("250.50"), updatedOrderDetail.getPrice());
    }

    @Test
    void deleteOrderDetail() {
        // Given: Setup object or precondition
        orderDetailRepository.save(orderDetail1);

        // When: Action or behavior that we are going to test
        orderDetailRepository.deleteById(orderDetail1.getId());
        Optional<OrderDetail> getOrderDetail1 = orderDetailRepository.findById(orderDetail1.getId());

        // Then: Verify the output or expected result
        assertThat(getOrderDetail1).isEmpty();
    }

    @Test
    void findByOrderId() {
        // Given : Setup object or precondition
        orderDetailRepository.save(orderDetail1);
        orderDetailRepository.save(orderDetail2);
        orderDetailRepository.save(orderDetail3);

        // When : Action of behavious that we are going to test
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order1.getId());

        // Then : Verify the output
        assertNotNull(orderDetails);
        assertTrue(orderDetails.contains(orderDetail1));
        assertTrue(orderDetails.contains(orderDetail2));
        assertFalse(orderDetails.contains(orderDetail3));
    }

    @Test
    void findByProductId() {
        // Given : Setup object or precondition
        orderDetailRepository.save(orderDetail1);
        orderDetailRepository.save(orderDetail2);
        orderDetailRepository.save(orderDetail3);

        // When : Action of behavious that we are going to test
        List<OrderDetail> orderDetails = orderDetailRepository.findByProductId(product1.getId());

        // Then : Verify the output
        assertNotNull(orderDetails);
        assertTrue(orderDetails.contains(orderDetail1));
        assertFalse(orderDetails.contains(orderDetail2));
        assertTrue(orderDetails.contains(orderDetail3));
    }

    @Test
    void deleteByOrderId() {
        // Given: Setup object or precondition
        orderDetailRepository.save(orderDetail1);
        orderDetailRepository.save(orderDetail2);
        orderDetailRepository.save(orderDetail3);

        // When: Action or behavior that we are going to test
        orderDetailRepository.deleteByOrderId(order1.getId());
        Optional<OrderDetail> getOrderDetail1 = orderDetailRepository.findById(orderDetail1.getId());
        Optional<OrderDetail> getOrderDetail2 = orderDetailRepository.findById(orderDetail2.getId());
        Optional<OrderDetail> getOrderDetail3 = orderDetailRepository.findById(orderDetail3.getId());

        // Then: Verify the output or expected result
        assertThat(getOrderDetail1).isEmpty();
        assertThat(getOrderDetail2).isEmpty();
        assertNotNull(getOrderDetail3);

    }

}