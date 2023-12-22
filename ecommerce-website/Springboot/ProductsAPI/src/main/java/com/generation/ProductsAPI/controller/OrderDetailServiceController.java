package com.generation.ProductsAPI.controller;

import com.generation.ProductsAPI.service.OrderDetailService;
import com.generation.ProductsAPI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping
public class OrderDetailServiceController {

    OrderDetailService orderDetailService;

    OrderService orderService;

    public OrderDetailServiceController(@Autowired OrderDetailService orderDetailService, @Autowired OrderService orderService){
        this.orderDetailService = orderDetailService;
        this.orderService = orderService;
    }




}
