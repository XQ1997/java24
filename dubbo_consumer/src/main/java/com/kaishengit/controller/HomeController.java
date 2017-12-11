package com.kaishengit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kaishengit.service.ProductServcie;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class HomeController {

    @Reference(version = "1.2")
    private ProductServcie productServcie;

    public void sayHello() {
        List<String> stringList =productServcie.findAllProductNames();
        for(String str : stringList) {
            System.out.println(str);
        }
    }

}
