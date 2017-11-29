package com.kaishengit.controller;

import com.kaishengit.pojo.Product;
import com.kaishengit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public String home(Model model) {
        model.addAttribute("productList",productService.findAll());
        return "list";
    }

    @GetMapping("/new")
    public String newProduct() {
        return "new";
    }

    @PostMapping("/new")
    public String saveProduct(Product product) {
        productService.save(product);
        return "redirect:/product";
    }

}
