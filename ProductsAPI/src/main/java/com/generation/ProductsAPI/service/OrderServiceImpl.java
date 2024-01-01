package com.generation.ProductsAPI.service;

import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(@Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrder(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order createNewOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> updateOrder(Integer id, Order order) {

        Optional<Order> result = orderRepository.findById(id);

        try{
            Order temp = result.get();
            temp.setUsername(order.getUsername());
            temp.setSubtotal(order.getSubtotal());
            temp.setShippingFee(order.getShippingFee());
            temp.setTotal(order.getTotal());
            return Optional.of(orderRepository.save(temp));
        }catch(Exception e){
            return result;
        }
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

}
