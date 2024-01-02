package com.generation.ProductsAPI.controller;

import com.generation.ProductsAPI.exception.ResourceNotFoundException;
import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.model.OrderDetail;
import com.generation.ProductsAPI.model.Product;
import com.generation.ProductsAPI.service.OrderDetailService;
import com.generation.ProductsAPI.service.OrderService;
import com.generation.ProductsAPI.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping
public class OrderDetailServiceController {

    OrderDetailService orderDetailService;

    OrderService orderService;

    ProductService productService;

    public OrderDetailServiceController(@Autowired OrderDetailService orderDetailService, @Autowired OrderService orderService, @Autowired ProductService productService){
        this.orderDetailService = orderDetailService;
        this.orderService = orderService;
        this.productService = productService;
    }


    @GetMapping("/api/order-details")
    public ResponseEntity<Object> getAllOrderDetails() {

        List<OrderDetail> result = orderDetailService.getAllOrderDetails();

        if(result.isEmpty()) {
            throw new ResourceNotFoundException("No order detail(s) found");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/api/order-details/{id}")
    public ResponseEntity<Object> getSingleOrderDetail(@PathVariable Integer id){

        OrderDetail result = orderDetailService.getOrderDetail(id).orElseThrow(() -> new ResourceNotFoundException("Order Detail of id " + id + " is not found"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PostMapping("/order-details")
//    public ResponseEntity<Object> createNewOrderDetail(@Valid @RequestBody OrderDetail orderDetail) {
//
//        return new ResponseEntity<>(orderDetailService.createNewOrderDetail(orderDetail), HttpStatus.CREATED);
//    }

    @PutMapping("/api/order-details/{id}")
    public ResponseEntity<Object> updateOrderDetail(@PathVariable Integer id ,@Valid @RequestBody OrderDetail orderDetail) {

        OrderDetail result = orderDetailService.updateOrderDetail(id, orderDetail).orElseThrow(() -> new ResourceNotFoundException("Order Detail of id " + id + " is not found"));;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/api/order-details/{id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable Integer id) {

        orderDetailService.getOrderDetail(id).orElseThrow(() -> new ResourceNotFoundException("Order Detail of id " + id + " is not found"));

        orderDetailService.deleteOrderDetail(id);

        return new ResponseEntity<>("Order Detail is deleted successfully.", HttpStatus.OK);
    }


    @GetMapping("/api/orders/{orderId}/order-details")
    public ResponseEntity<Object> getAllOrderDetailsByOrderId(@PathVariable Integer orderId){

        orderService.getOrder(orderId).orElseThrow(() -> new ResourceNotFoundException("Order of id " + orderId + " is not found"));

        List<OrderDetail> result = orderDetailService.getAllOrderDetailsByOrderId(orderId);

        if(result.isEmpty()) {
            throw new ResourceNotFoundException("No order details(s) found");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/api/orders/{orderId}/products/{productId}/order-details")
    public ResponseEntity<Object> createNewOrderDetailsByOrderId(@PathVariable Integer orderId, @PathVariable Integer productId, @Valid @RequestBody OrderDetail orderDetail) {

//        OrderDetail result = orderService.getOrder(orderId).map(order -> {
//            orderDetail.setOrder(order);
//            return orderDetailService.createNewOrderDetail(orderDetail);
//        }).orElseThrow(() -> new ResourceNotFoundException("Order of id " + orderId + " is not found"));
//
//        return new ResponseEntity<>(result, HttpStatus.CREATED);

        Order order = orderService.getOrder(orderId).orElseThrow(() -> new ResourceNotFoundException("Order of id " + orderId + " is not found"));

        Product product = productService.getProduct(productId).orElseThrow(() -> new ResourceNotFoundException("Product of id " + productId + " is not found"));

        orderDetail.setOrder(order);
        orderDetail.setProduct(product);

        return new ResponseEntity<>(orderDetailService.createNewOrderDetail(orderDetail), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/orders/{orderId}/order-details")
    public ResponseEntity<String> deleteAllOrderDetailByOrderId(@PathVariable Integer orderId) {

        orderService.getOrder(orderId).orElseThrow(() -> new ResourceNotFoundException("Order of id " + orderId + " is not found"));

        orderDetailService.deleteAllOrderDetailByOrderId(orderId);

        return new ResponseEntity<>("All order details from Order " + orderId + " are deleted successfully.", HttpStatus.OK);
    }



}
