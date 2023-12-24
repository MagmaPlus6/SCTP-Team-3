package com.generation.ProductsAPI.repository;

import com.generation.ProductsAPI.model.OrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    List<OrderDetail> findByOrderId(Integer orderId);

    List<OrderDetail> findByProductId(Integer productId);

    @Transactional
    void deleteByOrderId(Integer orderId);

}
