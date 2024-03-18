package com.thymeleaf.bankingatelier.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//map to home dashboard
@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    public String home() {
        return "home";
    }

}




