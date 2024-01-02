package com.generation.ProductsAPI.controller;

import com.generation.ProductsAPI.exception.ResourceNotFoundException;
import com.generation.ProductsAPI.model.Order;
import com.generation.ProductsAPI.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderServiceController {

    OrderService orderService;

    public OrderServiceController(@Autowired OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllOrders() {

        List<Order> result = orderService.getAllOrders();

        if(result.isEmpty()) {
            throw new ResourceNotFoundException("No order(s) found");
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleOrder(@PathVariable Integer id){

        Order result = orderService.getOrder(id).orElseThrow(() -> new ResourceNotFoundException("Order of id " + id + " is not found"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> createNewOrder(@Valid @RequestBody Order order) {

        return new ResponseEntity<>(orderService.createNewOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable Integer id ,@Valid @RequestBody Order order) {

        Order result = orderService.updateOrder(id, order).orElseThrow(() -> new ResourceNotFoundException("Order of id " + id + " is not found"));;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {

        orderService.getOrder(id).orElseThrow(() -> new ResourceNotFoundException("Order of id " + id + " is not found"));

        orderService.deleteOrder(id);

        return new ResponseEntity<>("Order is deleted successfully.", HttpStatus.OK);
    }


}
