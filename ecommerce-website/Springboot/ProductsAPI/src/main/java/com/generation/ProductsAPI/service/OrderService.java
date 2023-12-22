package com.generation.ProductsAPI.service;

import com.generation.ProductsAPI.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public abstract List<Order> getAllOrders();
    public abstract Optional<Order> getOrder(Integer id);
    public abstract Order createNewOrder(Order order);
    public abstract Optional<Order> updateOrder(Integer id, Order order);
    public abstract void deleteOrder(Integer id);

}
