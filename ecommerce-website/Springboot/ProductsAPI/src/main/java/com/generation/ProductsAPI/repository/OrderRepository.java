package com.generation.ProductsAPI.repository;

import com.generation.ProductsAPI.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {




}
