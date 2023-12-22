package com.generation.ProductsAPI.service;

import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.model.OrderDetail;
import com.generation.ProductsAPI.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(@Autowired OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(Integer orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByProductId(Integer productId) {
        return orderDetailRepository.findByProductId(productId);
    }

    @Override
    public Optional<OrderDetail> getOrderDetail(Integer id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail createNewOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public Optional<OrderDetail> updateOrderDetail(Integer id, OrderDetail orderDetail) {

        Optional<OrderDetail> result = orderDetailRepository.findById(id);

        try{
            OrderDetail temp = result.get();
            temp.setOrder(orderDetail.getOrder());
            temp.setProduct(orderDetail.getProduct());
            temp.setSize(orderDetail.getSize());
            temp.setQuantity(orderDetail.getQuantity());
            temp.setPrice(orderDetail.getPrice());
            return Optional.of(orderDetailRepository.save(temp));
        }catch(Exception e){
            return result;
        }
    }

    @Override
    public void deleteOrderDetail(Integer id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public void deleteAllOrderDetailByOrderId(Integer orderId) {
        orderDetailRepository.deleteByOrderId(orderId);
    }

}
