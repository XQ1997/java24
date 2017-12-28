package com.kaishengit.controller;

import com.kaishengit.pojo.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/order")
    public Order findOrder() {
        return new Order("20171228082345001","已发货",45.67F);
    }
}
