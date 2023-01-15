package com.example.asincrona10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/clientes"})
    public String index() {
        return "home";
    }
}
