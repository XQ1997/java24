package com.kaishengit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kaishengit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Reference
    private UserService userService;

    @GetMapping("/dubbo")
    public String hello() {
        userService.sayHello("SpringBoot+dubbo");
        return "hello,dubbo";
    }

}
