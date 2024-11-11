package com.icispp.notificationservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String hello() {
        return "demo(delete)";
    }
}
