package com.kaishengit.controller;

import com.kaishengit.pojo.Product;
import com.kaishengit.service.ProductService;
import com.kaishengit.util.Page;
import com.kaishengit.util.RequestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public String home(Model model,
                       HttpServletRequest request,
                       @RequestParam(required = false,name = "p",defaultValue = "1") Integer pageNo) {
        List<RequestQuery> requestQueryList = RequestQuery.builderRequestQuery(request);
        Page<Product> productList = productService.findByRequestQuery(requestQueryList,pageNo);
        model.addAttribute("page",productList);
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

    @GetMapping("/{id:\\d+}")
    public String viewProduct(@PathVariable Integer id,Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "product";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String editProduct(@PathVariable Integer id,Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String editProduct(Product product) {
        productService.save(product);
        return "redirect:/product/"+product.getId();
    }

    @GetMapping("/{id:\\d+}/delete")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return "redirect:/product";
    }

}
