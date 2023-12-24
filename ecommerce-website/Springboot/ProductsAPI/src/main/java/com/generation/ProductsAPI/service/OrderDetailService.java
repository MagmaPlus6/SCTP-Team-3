package com.generation.ProductsAPI.service;

import com.generation.ProductsAPI.model.OrderDetail;
import com.generation.ProductsAPI.model.Product;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {

    public abstract List<OrderDetail> getAllOrderDetails();
    public abstract Optional<OrderDetail> getOrderDetail(Integer id);
    public abstract OrderDetail createNewOrderDetail(OrderDetail orderDetail);
    public abstract Optional<OrderDetail> updateOrderDetail(Integer id, OrderDetail orderDetail);
    public abstract void deleteOrderDetail(Integer id);
    public abstract List<OrderDetail> getAllOrderDetailsByOrderId(Integer orderId);
    public abstract List<OrderDetail> getAllOrderDetailsByProductId(Integer productId);
    public abstract void deleteAllOrderDetailByOrderId(Integer orderId);
}
