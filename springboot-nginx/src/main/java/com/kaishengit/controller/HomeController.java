package com.kaishengit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Value("${appName}")
    String appName;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        model.addAttribute("appName",appName);
        model.addAttribute("sessionId",session.getId());
        return "index";
    }
}
