package com.mirahtec.apisiraparents.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
        @GetMapping("/home")
        public String home() {
            return "Welcome to the API of the Parents' Sira Application!";
        }
}
