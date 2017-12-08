package com.kaishengit.controller;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;
import com.kaishengit.entity.User;
import com.kaishengit.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/save")
    @ResponseBody
    public String save() {
        Product product = new Product();
        product.setProductName("iPhone手机");
        product.setProductInventory(100);
        productMapper.save(product);
        return "Hi";
    }

    @GetMapping("/hello")
    public String hello(Model model, HttpSession session) {

        model.addAttribute("message","SpringBoot Demo 1.0");
        model.addAttribute("user",new User(1009,"lisi","北京"));
        session.setAttribute("msg","Session Message");

        List<String> userNameList = Arrays.asList("aa","bb","cc","dd");
        model.addAttribute("userNameList",userNameList);

        return "index";
    }
}
