package com.generation.ProductsAPI.controller;

import com.generation.ProductsAPI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderServiceController {

    OrderService orderService;

    public OrderServiceController(@Autowired OrderService orderService){
        this.orderService = orderService;
    }
















}
